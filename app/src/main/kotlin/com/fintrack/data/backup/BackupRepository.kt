package com.fintrack.data.backup

import androidx.room.withTransaction
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.AimAllocationEntity
import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.LoanEntity
import com.fintrack.data.db.entities.LoanValueEntity
import com.fintrack.data.db.entities.MilestoneEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.domain.model.GoalType
import com.fintrack.domain.model.MilestoneType
import com.fintrack.domain.util.formatted
import com.fintrack.domain.util.parseDateLenient
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import java.math.BigDecimal
import java.util.UUID
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream
import javax.inject.Inject
import javax.inject.Singleton

data class ImportSummary(val snapshotsAdded: Int, val holdingsAdded: Int, val targetUser: String)

class UnsupportedBackupVersionException(val foundVersion: Int) :
    RuntimeException(
        "This backup was produced by an older build (schema v$foundVersion). " +
            "v3 requires a fresh start.",
    )

private val BackupJson = Json {
    prettyPrint = true
    encodeDefaults = true
    ignoreUnknownKeys = true
}

@Singleton
class BackupRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    // ------------------------------------------------------------------ JSON

    suspend fun exportEncryptedJson(
        activeUserId: UUID,
        passphrase: CharArray,
        out: OutputStream,
    ) {
        val payload = buildPayload(activeUserId)
        val bytes = BackupJson.encodeToString(BackupPayload.serializer(), payload)
            .toByteArray(Charsets.UTF_8)
        CryptoBox.encrypt(passphrase, bytes, out)
    }

    suspend fun importEncryptedJson(
        targetUserId: UUID,
        passphrase: CharArray,
        input: InputStream,
    ): ImportSummary {
        val decrypted = CryptoBox.decrypt(passphrase, input)
        val text = decrypted.toString(Charsets.UTF_8)
        val payload = BackupJson.decodeFromString(BackupPayload.serializer(), text)
        if (payload.version != BackupPayload.SCHEMA_VERSION) {
            throw UnsupportedBackupVersionException(payload.version)
        }
        return applyPayload(targetUserId, payload)
    }

    // ------------------------------------------------------------------ CSV

    /**
     * v3 CSV export uses one header row that names the schema version so the
     * import path can reject mismatched files. Snapshots/HoldingValues/Loans/
     * LoanValues/AimAllocations land in separate CSVs in the same zip.
     */
    suspend fun exportCsvZip(activeUserId: UUID, out: OutputStream) {
        val payload = buildPayload(activeUserId)
        ZipOutputStream(out).use { zip ->
            writeCsvEntry(zip, "version.txt", listOf("schema_version"), listOf(listOf(payload.version.toString())))
            writeCsvEntry(zip, "asset_classes.csv", AssetClassesCsvHeader, payload.assetClasses.map { listOf(
                it.id, it.name, it.displayOrder.toString(), it.isSeeded.toString(),
                it.isActive.toString(), it.createdAt,
            ) })
            writeCsvEntry(zip, "sub_buckets.csv", SubBucketsCsvHeader, payload.subBuckets.map { listOf(
                it.id, it.assetClassId, it.name, it.displayOrder.toString(),
                it.isSeeded.toString(), it.isActive.toString(), it.createdAt,
            ) })
            writeCsvEntry(zip, "holdings.csv", HoldingsCsvHeader, payload.holdings.map { listOf(
                it.id, it.subBucketId, it.name,
                it.trackInvested.toString(), it.trackSip.toString(),
                it.isActive.toString(), it.displayOrder.toString(),
            ) })
            writeCsvEntry(zip, "aim_allocations.csv", AimAllocationsCsvHeader, payload.aimAllocations.map { listOf(
                it.userId, it.assetClassId, it.aimPercent.toString(),
            ) })
            writeCsvEntry(zip, "snapshots.csv", SnapshotsCsvHeader, payload.snapshots.map { listOf(
                it.id, it.userId, csvDate(it.snapshotDate), it.earningsInCr,
                it.notes.orEmpty(), it.createdAt, it.updatedAt,
            ) })
            writeCsvEntry(zip, "holding_values.csv", HoldingValuesCsvHeader, payload.holdingValues.map { listOf(
                it.id, it.snapshotId, it.holdingId,
                it.invested.orEmpty(), it.current, it.sip.orEmpty(),
            ) })
            writeCsvEntry(zip, "loans.csv", LoansCsvHeader, payload.loans.map { listOf(
                it.id, it.userId, it.name, it.originalAmount, csvDate(it.takenDate),
                it.monthlyEmi, it.isActive.toString(),
                it.closedDate?.let(::csvDate).orEmpty(),
                it.createdAt, it.updatedAt,
            ) })
            writeCsvEntry(zip, "loan_values.csv", LoanValuesCsvHeader, payload.loanValues.map { listOf(
                it.id, it.snapshotId, it.loanId, it.outstanding,
            ) })
            writeCsvEntry(zip, "goals.csv", GoalsCsvHeader, payload.goals.map { listOf(
                it.id, it.userId, it.name, it.goalType,
                it.targetNetWorth, csvDate(it.targetDate),
                it.createdAt, it.achievedAt?.let(::csvDate).orEmpty(),
                it.isArchived.toString(),
            ) })
            writeCsvEntry(zip, "milestones.csv", MilestonesCsvHeader, payload.milestones.map { listOf(
                it.id, it.userId, it.type, it.achievedAtSnapshotId,
                csvDate(it.achievedAtDate),
                it.amountAtAchievement.orEmpty(), it.isCelebrated.toString(),
                it.createdAt,
            ) })
            payload.streakState?.let { ss ->
                writeCsvEntry(zip, "streak_state.csv", StreakStateCsvHeader, listOf(listOf(
                    ss.userId, ss.currentStreakMonths.toString(),
                    ss.longestStreakMonths.toString(),
                    ss.lastSnapshotMonth.orEmpty(),
                )))
            }
        }
    }

    suspend fun importCsvZip(targetUserId: UUID, input: InputStream): ImportSummary {
        var version: Int? = null
        var assetClasses = emptyList<AssetClassDto>()
        var subBuckets = emptyList<SubBucketDto>()
        var holdings = emptyList<HoldingDto>()
        var aimAllocations = emptyList<AimAllocationDto>()
        var snapshots = emptyList<SnapshotDto>()
        var holdingValues = emptyList<HoldingValueDto>()
        var loans = emptyList<LoanDto>()
        var loanValues = emptyList<LoanValueDto>()
        var goals = emptyList<GoalDto>()
        var milestones = emptyList<MilestoneDto>()
        var streakState: StreakStateDto? = null

        ZipInputStream(input).use { zip ->
            var entry: ZipEntry? = zip.nextEntry
            while (entry != null) {
                val text = zip.readBytes().toString(Charsets.UTF_8)
                when (entry.name) {
                    "version.txt" -> {
                        val rows = CsvCodec.parse(text)
                        val data = rows.drop(1).firstOrNull()?.firstOrNull()
                        version = data?.toIntOrNull()
                    }
                    "asset_classes.csv" -> assetClasses = parseCsv(text, AssetClassesCsvHeader) { row ->
                        AssetClassDto(
                            id = row[0], name = row[1],
                            displayOrder = row[2].toIntOrNull() ?: 0,
                            isSeeded = row[3].toBooleanLenient(),
                            isActive = row[4].toBooleanLenient(),
                            createdAt = row[5],
                        )
                    }
                    "sub_buckets.csv" -> subBuckets = parseCsv(text, SubBucketsCsvHeader) { row ->
                        SubBucketDto(
                            id = row[0], assetClassId = row[1], name = row[2],
                            displayOrder = row[3].toIntOrNull() ?: 0,
                            isSeeded = row[4].toBooleanLenient(),
                            isActive = row[5].toBooleanLenient(),
                            createdAt = row[6],
                        )
                    }
                    "holdings.csv" -> holdings = parseCsv(text, HoldingsCsvHeader) { row ->
                        HoldingDto(
                            id = row[0], subBucketId = row[1], name = row[2],
                            trackInvested = row[3].toBooleanLenient(),
                            trackSip = row[4].toBooleanLenient(),
                            isActive = row[5].toBooleanLenient(),
                            displayOrder = row[6].toIntOrNull() ?: 0,
                        )
                    }
                    "aim_allocations.csv" -> aimAllocations = parseCsv(text, AimAllocationsCsvHeader) { row ->
                        AimAllocationDto(
                            userId = row[0], assetClassId = row[1],
                            aimPercent = row[2].toIntOrNull() ?: 0,
                        )
                    }
                    "snapshots.csv" -> snapshots = parseCsv(text, SnapshotsCsvHeader) { row ->
                        SnapshotDto(
                            id = row[0], userId = row[1],
                            snapshotDate = parseCsvDate(row[2]),
                            earningsInCr = row[3], notes = row[4].ifBlank { null },
                            createdAt = row[5], updatedAt = row[6],
                        )
                    }
                    "holding_values.csv" -> holdingValues = parseCsv(text, HoldingValuesCsvHeader) { row ->
                        HoldingValueDto(
                            id = row[0], snapshotId = row[1], holdingId = row[2],
                            invested = row[3].ifBlank { null }, current = row[4],
                            sip = row[5].ifBlank { null },
                        )
                    }
                    "loans.csv" -> loans = parseCsv(text, LoansCsvHeader) { row ->
                        LoanDto(
                            id = row[0], userId = row[1], name = row[2],
                            originalAmount = row[3],
                            takenDate = parseCsvDate(row[4]),
                            monthlyEmi = row[5], isActive = row[6].toBooleanLenient(),
                            closedDate = row[7].ifBlank { null }?.let(::parseCsvDate),
                            createdAt = row[8], updatedAt = row[9],
                        )
                    }
                    "loan_values.csv" -> loanValues = parseCsv(text, LoanValuesCsvHeader) { row ->
                        LoanValueDto(
                            id = row[0], snapshotId = row[1], loanId = row[2],
                            outstanding = row[3],
                        )
                    }
                    "goals.csv" -> goals = parseCsv(text, GoalsCsvHeader) { row ->
                        GoalDto(
                            id = row[0], userId = row[1], name = row[2],
                            goalType = row[3], targetNetWorth = row[4],
                            targetDate = parseCsvDate(row[5]),
                            createdAt = row[6],
                            achievedAt = row[7].ifBlank { null }?.let(::parseCsvDate),
                            isArchived = row[8].toBooleanLenient(),
                        )
                    }
                    "milestones.csv" -> milestones = parseCsv(text, MilestonesCsvHeader) { row ->
                        MilestoneDto(
                            id = row[0], userId = row[1], type = row[2],
                            achievedAtSnapshotId = row[3],
                            achievedAtDate = parseCsvDate(row[4]),
                            amountAtAchievement = row[5].ifBlank { null },
                            isCelebrated = row[6].toBooleanLenient(),
                            createdAt = row[7],
                        )
                    }
                    "streak_state.csv" -> {
                        val rows = parseCsv(text, StreakStateCsvHeader) { row ->
                            StreakStateDto(
                                userId = row[0],
                                currentStreakMonths = row[1].toIntOrNull() ?: 0,
                                longestStreakMonths = row[2].toIntOrNull() ?: 0,
                                lastSnapshotMonth = row[3].ifBlank { null },
                            )
                        }
                        streakState = rows.firstOrNull()
                    }
                }
                zip.closeEntry()
                entry = zip.nextEntry
            }
        }

        if (version != null && version != BackupPayload.SCHEMA_VERSION) {
            throw UnsupportedBackupVersionException(version!!)
        }

        // CSV imports always land under the active user.
        val payload = BackupPayload(
            version = BackupPayload.SCHEMA_VERSION,
            exportedAt = Clock.System.now().toString(),
            activeUserId = targetUserId.toString(),
            users = emptyList(),
            assetClasses = assetClasses,
            subBuckets = subBuckets,
            holdings = holdings,
            aimAllocations = aimAllocations,
            snapshots = snapshots,
            holdingValues = holdingValues,
            loans = loans,
            loanValues = loanValues,
            goals = goals,
            milestones = milestones,
            streakState = streakState,
        )
        return applyPayload(targetUserId, payload)
    }

    // --------------------------------------------------------------- shared

    private suspend fun buildPayload(activeUserId: UUID): BackupPayload {
        val userDao = database.userDao()
        val acDao = database.assetClassDao()
        val sbDao = database.subBucketDao()
        val holdingDao = database.holdingDao()
        val aimDao = database.aimAllocationDao()
        val snapshotDao = database.snapshotDao()
        val valueDao = database.holdingValueDao()
        val loanDao = database.loanDao()
        val loanValueDao = database.loanValueDao()
        val goalDao = database.goalDao()
        val milestoneDao = database.milestoneDao()
        val streakDao = database.streakStateDao()

        val user = userDao.getUser(activeUserId)
            ?: error("Active user $activeUserId not found")
        val assetClasses = acDao.observeAll().first()
        val subBuckets = sbDao.observeAll().first()
        val holdings = holdingDao.observeAll().first()
        val aimAllocations = aimDao.getForUser(activeUserId)
        val snapshots = snapshotDao.observeForUser(activeUserId).first()
        val values = valueDao.observeAllForUser(activeUserId).first()
        val loans = loanDao.observeForUser(activeUserId).first()
        val loanValues = loanValueDao.observeAllForUser(activeUserId).first()
        val goals = goalDao.observeAllForUser(activeUserId).first()
        val milestones = milestoneDao.getAllForUser(activeUserId)
        val streak = streakDao.getForUser(activeUserId)

        return BackupPayload(
            version = BackupPayload.SCHEMA_VERSION,
            exportedAt = Clock.System.now().toString(),
            activeUserId = activeUserId.toString(),
            users = listOf(UserDto(
                id = user.id.toString(), name = user.name, colorHex = user.colorHex,
                createdAt = user.createdAt.toString(), isActive = user.isActive,
            )),
            assetClasses = assetClasses.map { AssetClassDto(
                id = it.id.toString(), name = it.name, displayOrder = it.displayOrder,
                isSeeded = it.isSeeded, isActive = it.isActive,
                createdAt = it.createdAt.toString(),
            ) },
            subBuckets = subBuckets.map { SubBucketDto(
                id = it.id.toString(), assetClassId = it.assetClassId.toString(),
                name = it.name, displayOrder = it.displayOrder,
                isSeeded = it.isSeeded, isActive = it.isActive,
                createdAt = it.createdAt.toString(),
            ) },
            holdings = holdings.map { HoldingDto(
                id = it.id.toString(), subBucketId = it.subBucketId.toString(),
                name = it.name, trackInvested = it.trackInvested,
                trackSip = it.trackSip, isActive = it.isActive,
                displayOrder = it.displayOrder,
            ) },
            aimAllocations = aimAllocations.map { AimAllocationDto(
                userId = it.userId.toString(),
                assetClassId = it.assetClassId.toString(),
                aimPercent = it.aimPercent,
            ) },
            snapshots = snapshots.map { SnapshotDto(
                id = it.id.toString(), userId = it.userId.toString(),
                snapshotDate = it.snapshotDate.toString(),
                earningsInCr = it.earningsInCr.toPlainString(), notes = it.notes,
                createdAt = it.createdAt.toString(), updatedAt = it.updatedAt.toString(),
            ) },
            holdingValues = values.map { HoldingValueDto(
                id = it.id.toString(), snapshotId = it.snapshotId.toString(),
                holdingId = it.holdingId.toString(),
                invested = it.invested?.toPlainString(),
                current = it.current.toPlainString(),
                sip = it.sip?.toPlainString(),
            ) },
            loans = loans.map { LoanDto(
                id = it.id.toString(), userId = it.userId.toString(), name = it.name,
                originalAmount = it.originalAmount.toPlainString(),
                takenDate = it.takenDate.toString(),
                monthlyEmi = it.monthlyEmi.toPlainString(),
                isActive = it.isActive,
                closedDate = it.closedDate?.toString(),
                createdAt = it.createdAt.toString(),
                updatedAt = it.updatedAt.toString(),
            ) },
            loanValues = loanValues.map { LoanValueDto(
                id = it.id.toString(), snapshotId = it.snapshotId.toString(),
                loanId = it.loanId.toString(),
                outstanding = it.outstanding.toPlainString(),
            ) },
            goals = goals.map { GoalDto(
                id = it.id.toString(), userId = it.userId.toString(), name = it.name,
                goalType = it.goalType.name,
                targetNetWorth = it.targetNetWorth.toPlainString(),
                targetDate = it.targetDate.toString(),
                createdAt = it.createdAt.toString(),
                achievedAt = it.achievedAt?.toString(),
                isArchived = it.isArchived,
            ) },
            milestones = milestones.map { MilestoneDto(
                id = it.id.toString(), userId = it.userId.toString(),
                type = it.type.name,
                achievedAtSnapshotId = it.achievedAtSnapshotId.toString(),
                achievedAtDate = it.achievedAtDate.toString(),
                amountAtAchievement = it.amountAtAchievement?.toPlainString(),
                isCelebrated = it.isCelebrated,
                createdAt = it.createdAt.toString(),
            ) },
            streakState = streak?.let {
                StreakStateDto(
                    userId = it.userId.toString(),
                    currentStreakMonths = it.currentStreakMonths,
                    longestStreakMonths = it.longestStreakMonths,
                    lastSnapshotMonth = it.lastSnapshotMonth,
                )
            },
        )
    }

    @Suppress("LongMethod")
    private suspend fun applyPayload(targetUserId: UUID, payload: BackupPayload): ImportSummary {
        val userDao = database.userDao()
        val holdingDao = database.holdingDao()
        val aimDao = database.aimAllocationDao()
        val snapshotDao = database.snapshotDao()
        val valueDao = database.holdingValueDao()
        val loanDao = database.loanDao()
        val loanValueDao = database.loanValueDao()
        val goalDao = database.goalDao()
        val milestoneDao = database.milestoneDao()
        val streakDao = database.streakStateDao()

        val targetUser = userDao.getUser(targetUserId)
            ?: error("Target user $targetUserId not found on this device")

        // Reconcile holdings by id; if the id doesn't exist locally, additively
        // insert. SubBucket reconciliation is by id too — caller must have already
        // imported any new asset-class/sub-bucket rows or the FK insert will fail.
        val existingHoldings = holdingDao.observeAll().first()
        val existingHoldingIds = existingHoldings.map { it.id }.toSet()
        val holdingIdMap = mutableMapOf<UUID, UUID>()
        val newHoldings = mutableListOf<com.fintrack.data.db.entities.HoldingEntity>()
        for (dto in payload.holdings) {
            val originalId = UUID.fromString(dto.id)
            if (originalId in existingHoldingIds) {
                holdingIdMap[originalId] = originalId
            } else {
                holdingIdMap[originalId] = originalId
                newHoldings += com.fintrack.data.db.entities.HoldingEntity(
                    id = originalId,
                    subBucketId = UUID.fromString(dto.subBucketId),
                    name = dto.name,
                    trackInvested = dto.trackInvested,
                    trackSip = dto.trackSip,
                    isActive = dto.isActive,
                    displayOrder = dto.displayOrder,
                )
            }
        }

        // Map original loan id → target loan id (auto-create if missing).
        val existingLoans = loanDao.observeForUser(targetUser.id).first()
        val existingLoanByName = existingLoans.associateBy { it.name }
        val loanIdMap = mutableMapOf<UUID, UUID>()
        val newLoans = mutableListOf<LoanEntity>()
        for (dto in payload.loans) {
            val original = UUID.fromString(dto.id)
            val match = existingLoanByName[dto.name]
            if (match != null) {
                loanIdMap[original] = match.id
            } else {
                val newId = UUID.randomUUID()
                loanIdMap[original] = newId
                newLoans += LoanEntity(
                    id = newId,
                    userId = targetUser.id,
                    name = dto.name,
                    originalAmount = BigDecimal(dto.originalAmount),
                    takenDate = LocalDate.parse(dto.takenDate),
                    monthlyEmi = BigDecimal(dto.monthlyEmi),
                    isActive = dto.isActive,
                    closedDate = dto.closedDate?.let(LocalDate::parse),
                    createdAt = parseInstantSafely(dto.createdAt) ?: Clock.System.now(),
                    updatedAt = parseInstantSafely(dto.updatedAt) ?: Clock.System.now(),
                )
            }
        }

        val now = Clock.System.now()
        var snapshotsAdded = 0
        val snapshotIdMap = mutableMapOf<String, UUID>()

        database.withTransaction {
            if (newHoldings.isNotEmpty()) holdingDao.insertAll(newHoldings)
            for (loan in newLoans) loanDao.insert(loan)

            // Snapshots always land under the target user, regardless of file's userId.
            for (sdto in payload.snapshots) {
                val newSnapshotId = UUID.randomUUID()
                snapshotIdMap[sdto.id] = newSnapshotId
                val snapshot = SnapshotEntity(
                    id = newSnapshotId,
                    userId = targetUser.id,
                    snapshotDate = parseDateLenient(sdto.snapshotDate),
                    earningsInCr = BigDecimal(sdto.earningsInCr),
                    notes = sdto.notes,
                    createdAt = parseInstantSafely(sdto.createdAt) ?: now,
                    updatedAt = parseInstantSafely(sdto.updatedAt) ?: now,
                )
                snapshotDao.insert(snapshot)
                snapshotsAdded++

                val rowsForSnapshot = payload.holdingValues.filter { it.snapshotId == sdto.id }
                val mapped = rowsForSnapshot.mapNotNull { vdto ->
                    val mappedId = holdingIdMap[UUID.fromString(vdto.holdingId)]
                        ?: return@mapNotNull null
                    HoldingValueEntity(
                        id = UUID.randomUUID(),
                        snapshotId = newSnapshotId,
                        holdingId = mappedId,
                        invested = vdto.invested?.let(::BigDecimal),
                        current = BigDecimal(vdto.current),
                        sip = vdto.sip?.let(::BigDecimal),
                    )
                }
                if (mapped.isNotEmpty()) valueDao.insertAll(mapped)

                val loanValuesForSnap = payload.loanValues.filter { it.snapshotId == sdto.id }
                val mappedLoans = loanValuesForSnap.mapNotNull { lvDto ->
                    val mappedLoan = loanIdMap[UUID.fromString(lvDto.loanId)] ?: return@mapNotNull null
                    LoanValueEntity(
                        id = UUID.randomUUID(),
                        snapshotId = newSnapshotId,
                        loanId = mappedLoan,
                        outstanding = BigDecimal(lvDto.outstanding),
                    )
                }
                if (mappedLoans.isNotEmpty()) loanValueDao.insertAll(mappedLoans)
            }

            // Aim allocations for the target user.
            val ourAim = payload.aimAllocations.filter { it.userId == targetUser.id.toString() }
                .ifEmpty { payload.aimAllocations }
            if (ourAim.isNotEmpty()) {
                val rows = ourAim.map { dto ->
                    AimAllocationEntity(
                        userId = targetUser.id,
                        assetClassId = UUID.fromString(dto.assetClassId),
                        aimPercent = dto.aimPercent,
                    )
                }
                aimDao.upsertAll(rows)
            }

            // Goals re-targeted to the imported user.
            for (gdto in payload.goals) {
                goalDao.insert(GoalEntity(
                    id = UUID.randomUUID(),
                    userId = targetUser.id,
                    name = gdto.name,
                    goalType = runCatching { GoalType.valueOf(gdto.goalType) }.getOrDefault(GoalType.NET_WORTH),
                    targetNetWorth = BigDecimal(gdto.targetNetWorth),
                    targetDate = parseDateLenient(gdto.targetDate),
                    createdAt = parseInstantSafely(gdto.createdAt) ?: now,
                    achievedAt = gdto.achievedAt?.let(::parseDateLenient),
                    isArchived = gdto.isArchived,
                ))
            }

            // Milestones — re-key snapshot ids using the import map. Milestones
            // whose source snapshot wasn't in the payload are dropped (would
            // FK-fail otherwise). The detector re-runs on next save anyway.
            for (mdto in payload.milestones) {
                val newSnap = snapshotIdMap[mdto.achievedAtSnapshotId] ?: continue
                val type = runCatching { MilestoneType.valueOf(mdto.type) }.getOrNull() ?: continue
                milestoneDao.insert(com.fintrack.data.db.entities.MilestoneEntity(
                    id = UUID.randomUUID(),
                    userId = targetUser.id,
                    type = type,
                    achievedAtSnapshotId = newSnap,
                    achievedAtDate = parseDateLenient(mdto.achievedAtDate),
                    amountAtAchievement = mdto.amountAtAchievement?.let(::BigDecimal),
                    isCelebrated = mdto.isCelebrated,
                    createdAt = parseInstantSafely(mdto.createdAt) ?: now,
                ))
            }

            // Streak state — overwrite under the target user.
            payload.streakState?.let { ss ->
                streakDao.upsert(com.fintrack.data.db.entities.StreakStateEntity(
                    userId = targetUser.id,
                    currentStreakMonths = ss.currentStreakMonths,
                    longestStreakMonths = ss.longestStreakMonths,
                    lastSnapshotMonth = ss.lastSnapshotMonth,
                ))
            }
        }

        return ImportSummary(
            snapshotsAdded = snapshotsAdded,
            holdingsAdded = newHoldings.size,
            targetUser = targetUser.name,
        )
    }

    private fun parseInstantSafely(s: String): Instant? =
        runCatching { Instant.parse(s) }.getOrNull()

    private fun writeCsvEntry(
        zip: ZipOutputStream,
        name: String,
        header: List<String>,
        rows: List<List<String>>,
    ) {
        zip.putNextEntry(ZipEntry(name))
        zip.write(CsvCodec.encodeRow(header).toByteArray(Charsets.UTF_8))
        zip.write("\n".toByteArray(Charsets.UTF_8))
        for (row in rows) {
            zip.write(CsvCodec.encodeRow(row).toByteArray(Charsets.UTF_8))
            zip.write("\n".toByteArray(Charsets.UTF_8))
        }
        zip.closeEntry()
    }

    private inline fun <T> parseCsv(
        text: String,
        header: List<String>,
        transform: (List<String>) -> T,
    ): List<T> {
        val rows = CsvCodec.parse(text)
        if (rows.isEmpty()) return emptyList()
        val firstRow = rows.first()
        val dataRows = if (firstRow == header) rows.drop(1) else rows
        return dataRows.map { row ->
            val padded = if (row.size >= header.size) row
                else row + List(header.size - row.size) { "" }
            transform(padded)
        }
    }

    private companion object {
        val AssetClassesCsvHeader = listOf(
            "id", "name", "display_order", "is_seeded", "is_active", "created_at",
        )
        val SubBucketsCsvHeader = listOf(
            "id", "asset_class_id", "name", "display_order", "is_seeded", "is_active", "created_at",
        )
        val HoldingsCsvHeader = listOf(
            "id", "sub_bucket_id", "name", "track_invested", "track_sip",
            "is_active", "display_order",
        )
        val AimAllocationsCsvHeader = listOf("user_id", "asset_class_id", "aim_percent")
        val SnapshotsCsvHeader = listOf(
            "id", "user_id", "snapshot_date", "earnings_in_cr",
            "notes", "created_at", "updated_at",
        )
        val HoldingValuesCsvHeader = listOf(
            "id", "snapshot_id", "holding_id", "invested", "current", "sip",
        )
        val LoansCsvHeader = listOf(
            "id", "user_id", "name", "original_amount", "taken_date", "monthly_emi",
            "is_active", "closed_date", "created_at", "updated_at",
        )
        val LoanValuesCsvHeader = listOf("id", "snapshot_id", "loan_id", "outstanding")
        val GoalsCsvHeader = listOf(
            "id", "user_id", "name", "goal_type", "target_net_worth",
            "target_date", "created_at", "achieved_at", "is_archived",
        )
        val MilestonesCsvHeader = listOf(
            "id", "user_id", "type", "achieved_at_snapshot_id", "achieved_at_date",
            "amount_at_achievement", "is_celebrated", "created_at",
        )
        val StreakStateCsvHeader = listOf(
            "user_id", "current_streak_months", "longest_streak_months", "last_snapshot_month",
        )
    }
}

/**
 * Snapshots / loans / goals carry LocalDate fields that we render in CSV
 * with the user-friendly `dd-MMM-yyyy` form per spec §8 — JSON keeps the
 * canonical `yyyy-MM-dd` form. The DTO already holds a string, so we
 * only convert at the I/O edges.
 */
private fun csvDate(isoOrFormatted: String): String =
    runCatching { parseDateLenient(isoOrFormatted).formatted() }
        .getOrDefault(isoOrFormatted)

private fun parseCsvDate(text: String): String =
    runCatching { parseDateLenient(text).toString() }
        .getOrDefault(text)

private fun String.toBooleanLenient(): Boolean =
    when (lowercase().trim()) {
        "true", "1", "yes", "y" -> true
        else -> false
    }
