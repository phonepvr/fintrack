package com.fintrack.data.backup

import androidx.room.withTransaction
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.data.db.entities.UserSettingsEntity
import com.fintrack.domain.model.AssetClass
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import java.io.ByteArrayOutputStream
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
        val bytes = BackupJson.encodeToString(BackupPayload.serializer(), payload).toByteArray(Charsets.UTF_8)
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
        return applyPayload(targetUserId, payload)
    }

    // ------------------------------------------------------------------ CSV

    suspend fun exportCsvZip(activeUserId: UUID, out: OutputStream) {
        val payload = buildPayload(activeUserId)
        ZipOutputStream(out).use { zip ->
            writeCsvEntry(zip, "holdings.csv", HoldingsCsvHeader, payload.holdings.map { listOf(
                it.id, it.name, it.assetClass,
                it.trackInvested.toString(), it.trackSip.toString(),
                it.isBankAccount.toString(), it.isActive.toString(), it.displayOrder.toString(),
            ) })
            writeCsvEntry(zip, "snapshots.csv", SnapshotsCsvHeader, payload.snapshots.map { listOf(
                it.id, it.userId, it.snapshotDate, it.earningsInCr,
                it.notes.orEmpty(), it.createdAt, it.updatedAt,
            ) })
            writeCsvEntry(zip, "holding_values.csv", HoldingValuesCsvHeader, payload.holdingValues.map { listOf(
                it.id, it.snapshotId, it.holdingId,
                it.invested.orEmpty(), it.current, it.sip.orEmpty(),
            ) })
            writeCsvEntry(zip, "user_settings.csv", UserSettingsCsvHeader, payload.userSettings.map { listOf(
                it.userId, it.aimPctMfNps.toString(), it.aimPctEquity.toString(),
                it.aimPctFixedReturn.toString(), it.aimPctCrypto.toString(),
            ) })
        }
    }

    suspend fun importCsvZip(targetUserId: UUID, input: InputStream): ImportSummary {
        var holdings = emptyList<HoldingDto>()
        var snapshots = emptyList<SnapshotDto>()
        var holdingValues = emptyList<HoldingValueDto>()
        var userSettings = emptyList<UserSettingsDto>()

        ZipInputStream(input).use { zip ->
            var entry: ZipEntry? = zip.nextEntry
            while (entry != null) {
                val text = zip.readBytes().toString(Charsets.UTF_8)
                when (entry.name) {
                    "holdings.csv" -> holdings = parseCsv(text, HoldingsCsvHeader) { row -> HoldingDto(
                        id = row[0],
                        name = row[1],
                        assetClass = row[2],
                        trackInvested = row[3].toBooleanLenient(),
                        trackSip = row[4].toBooleanLenient(),
                        isBankAccount = row[5].toBooleanLenient(),
                        isActive = row[6].toBooleanLenient(),
                        displayOrder = row[7].toIntOrNull() ?: 0,
                    ) }
                    "snapshots.csv" -> snapshots = parseCsv(text, SnapshotsCsvHeader) { row -> SnapshotDto(
                        id = row[0],
                        userId = row[1],
                        snapshotDate = row[2],
                        earningsInCr = row[3],
                        notes = row[4].ifBlank { null },
                        createdAt = row[5],
                        updatedAt = row[6],
                    ) }
                    "holding_values.csv" -> holdingValues = parseCsv(text, HoldingValuesCsvHeader) { row -> HoldingValueDto(
                        id = row[0],
                        snapshotId = row[1],
                        holdingId = row[2],
                        invested = row[3].ifBlank { null },
                        current = row[4],
                        sip = row[5].ifBlank { null },
                    ) }
                    "user_settings.csv" -> userSettings = parseCsv(text, UserSettingsCsvHeader) { row ->
                        UserSettingsDto(
                            userId = row[0],
                            aimPctMfNps = row[1].toIntOrNull() ?: 55,
                            aimPctEquity = row[2].toIntOrNull() ?: 15,
                            aimPctFixedReturn = row[3].toIntOrNull() ?: 25,
                            aimPctCrypto = row[4].toIntOrNull() ?: 5,
                        )
                    }
                }
                zip.closeEntry()
                entry = zip.nextEntry
            }
        }

        // CSV imports don't carry the original active user id. Synthesize a
        // payload using the target user as the apply target.
        val payload = BackupPayload(
            version = 1,
            exportedAt = Clock.System.now().toString(),
            activeUserId = targetUserId.toString(),
            users = emptyList(), // CSV doesn't include a users.csv — keeps the file count manageable.
            userSettings = userSettings,
            holdings = holdings,
            snapshots = snapshots,
            holdingValues = holdingValues,
        )
        return applyPayload(targetUserId, payload)
    }

    // ------------------------------------------------------------------ shared

    private suspend fun buildPayload(activeUserId: UUID): BackupPayload {
        val userDao = database.userDao()
        val settingsDao = database.userSettingsDao()
        val holdingDao = database.holdingDao()
        val snapshotDao = database.snapshotDao()
        val valueDao = database.holdingValueDao()

        val user = userDao.getUser(activeUserId)
            ?: error("Active user $activeUserId not found")
        val settings = settingsDao.get(activeUserId)
            ?: error("Active user settings not found")
        val holdings = holdingDao.observeAll().first()
        val snapshots = snapshotDao.observeForUser(activeUserId).first()
        val values = valueDao.observeAllForUser(activeUserId).first()

        return BackupPayload(
            version = 1,
            exportedAt = Clock.System.now().toString(),
            activeUserId = activeUserId.toString(),
            users = listOf(UserDto(
                id = user.id.toString(),
                name = user.name,
                colorHex = user.colorHex,
                createdAt = user.createdAt.toString(),
                isActive = user.isActive,
            )),
            userSettings = listOf(UserSettingsDto(
                userId = settings.userId.toString(),
                aimPctMfNps = settings.aimPctMfNps,
                aimPctEquity = settings.aimPctEquity,
                aimPctFixedReturn = settings.aimPctFixedReturn,
                aimPctCrypto = settings.aimPctCrypto,
            )),
            holdings = holdings.map { h -> HoldingDto(
                id = h.id.toString(),
                name = h.name,
                assetClass = h.assetClass.name,
                trackInvested = h.trackInvested,
                trackSip = h.trackSip,
                isBankAccount = h.isBankAccount,
                isActive = h.isActive,
                displayOrder = h.displayOrder,
            ) },
            snapshots = snapshots.map { s -> SnapshotDto(
                id = s.id.toString(),
                userId = s.userId.toString(),
                snapshotDate = s.snapshotDate.toString(),
                earningsInCr = s.earningsInCr.toPlainString(),
                notes = s.notes,
                createdAt = s.createdAt.toString(),
                updatedAt = s.updatedAt.toString(),
            ) },
            holdingValues = values.map { v -> HoldingValueDto(
                id = v.id.toString(),
                snapshotId = v.snapshotId.toString(),
                holdingId = v.holdingId.toString(),
                invested = v.invested?.toPlainString(),
                current = v.current.toPlainString(),
                sip = v.sip?.toPlainString(),
            ) },
        )
    }

    private suspend fun applyPayload(targetUserId: UUID, payload: BackupPayload): ImportSummary {
        val userDao = database.userDao()
        val settingsDao = database.userSettingsDao()
        val holdingDao = database.holdingDao()
        val snapshotDao = database.snapshotDao()
        val valueDao = database.holdingValueDao()

        val targetUser = userDao.getUser(targetUserId)
            ?: error("Target user $targetUserId not found on this device")

        // Reconcile holdings: additive merge by (name, assetClass). Map old → new id.
        val existingHoldings = holdingDao.observeAll().first()
        val existingByKey = existingHoldings.associateBy { it.name to it.assetClass }
        val holdingIdMap = mutableMapOf<UUID, UUID>()
        val newHoldings = mutableListOf<HoldingEntity>()
        for (dto in payload.holdings) {
            val ac = AssetClass.valueOf(dto.assetClass)
            val key = dto.name to ac
            val existing = existingByKey[key]
            if (existing != null) {
                holdingIdMap[UUID.fromString(dto.id)] = existing.id
            } else {
                val newId = UUID.randomUUID()
                holdingIdMap[UUID.fromString(dto.id)] = newId
                newHoldings += HoldingEntity(
                    id = newId,
                    name = dto.name,
                    assetClass = ac,
                    trackInvested = dto.trackInvested,
                    trackSip = dto.trackSip,
                    isBankAccount = dto.isBankAccount,
                    isActive = dto.isActive,
                    displayOrder = dto.displayOrder,
                )
            }
        }

        val now = Clock.System.now()
        var snapshotsAdded = 0

        database.withTransaction {
            if (newHoldings.isNotEmpty()) {
                holdingDao.insertAll(newHoldings)
            }

            // Snapshots always land under the target (active) user, regardless of
            // the userId stored in the file.
            for (sdto in payload.snapshots) {
                val newSnapshotId = UUID.randomUUID()
                val snapshot = SnapshotEntity(
                    id = newSnapshotId,
                    userId = targetUser.id,
                    snapshotDate = LocalDate.parse(sdto.snapshotDate),
                    earningsInCr = BigDecimal(sdto.earningsInCr),
                    notes = sdto.notes,
                    createdAt = parseInstantSafely(sdto.createdAt) ?: now,
                    updatedAt = parseInstantSafely(sdto.updatedAt) ?: now,
                )
                snapshotDao.insert(snapshot)
                snapshotsAdded++

                val rowsForSnapshot = payload.holdingValues.filter { it.snapshotId == sdto.id }
                val mapped = rowsForSnapshot.mapNotNull { vdto ->
                    val newHoldingId = holdingIdMap[UUID.fromString(vdto.holdingId)] ?: return@mapNotNull null
                    HoldingValueEntity(
                        id = UUID.randomUUID(),
                        snapshotId = newSnapshotId,
                        holdingId = newHoldingId,
                        invested = vdto.invested?.let(::BigDecimal),
                        current = BigDecimal(vdto.current),
                        sip = vdto.sip?.let(::BigDecimal),
                    )
                }
                if (mapped.isNotEmpty()) valueDao.insertAll(mapped)
            }

            // Apply user settings if the file carries one for the same target user
            // (or if it carries exactly one settings row, treat it as the active user's).
            val matching = payload.userSettings.firstOrNull { it.userId == targetUser.id.toString() }
                ?: payload.userSettings.singleOrNull()
            if (matching != null) {
                require(matching.aimPctMfNps + matching.aimPctEquity +
                    matching.aimPctFixedReturn + matching.aimPctCrypto == 100) {
                    "Imported aim percentages must sum to 100"
                }
                settingsDao.upsert(UserSettingsEntity(
                    userId = targetUser.id,
                    aimPctMfNps = matching.aimPctMfNps,
                    aimPctEquity = matching.aimPctEquity,
                    aimPctFixedReturn = matching.aimPctFixedReturn,
                    aimPctCrypto = matching.aimPctCrypto,
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
            // Pad with empty strings if a row is short; tolerates lenient inputs.
            val padded = if (row.size >= header.size) row
                else row + List(header.size - row.size) { "" }
            transform(padded)
        }
    }

    private companion object {
        val HoldingsCsvHeader = listOf(
            "id", "name", "asset_class", "track_invested", "track_sip",
            "is_bank_account", "is_active", "display_order",
        )
        val SnapshotsCsvHeader = listOf(
            "id", "user_id", "snapshot_date", "earnings_in_cr",
            "notes", "created_at", "updated_at",
        )
        val HoldingValuesCsvHeader = listOf(
            "id", "snapshot_id", "holding_id",
            "invested", "current", "sip",
        )
        val UserSettingsCsvHeader = listOf(
            "user_id", "aim_pct_mf_nps", "aim_pct_equity",
            "aim_pct_fixed_return", "aim_pct_crypto",
        )
    }
}

private fun String.toBooleanLenient(): Boolean =
    when (lowercase().trim()) {
        "true", "1", "yes", "y" -> true
        else -> false
    }
