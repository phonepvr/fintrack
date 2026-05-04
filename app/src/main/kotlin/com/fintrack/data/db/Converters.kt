package com.fintrack.data.db

import androidx.room.TypeConverter
import com.fintrack.domain.model.GoalType
import com.fintrack.domain.model.MilestoneType
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.UUID

/**
 * BigDecimals are stored as fixed-scale strings (scale = 2, HALF_UP). All money
 * in the app is INR; we never persist Float/Double. Reading back preserves the
 * scale so equality comparisons in repositories stay deterministic.
 */
internal const val MONEY_SCALE = 2

private fun BigDecimal.normalise(): BigDecimal = setScale(MONEY_SCALE, RoundingMode.HALF_UP)

class Converters {

    @TypeConverter fun uuidToString(value: UUID?): String? = value?.toString()
    @TypeConverter fun stringToUuid(value: String?): UUID? = value?.let(UUID::fromString)

    @TypeConverter fun instantToString(value: Instant?): String? = value?.toString()
    @TypeConverter fun stringToInstant(value: String?): Instant? = value?.let(Instant::parse)

    @TypeConverter fun localDateToString(value: LocalDate?): String? = value?.toString()
    @TypeConverter fun stringToLocalDate(value: String?): LocalDate? = value?.let(LocalDate::parse)

    @TypeConverter fun bigDecimalToString(value: BigDecimal?): String? = value?.normalise()?.toPlainString()
    @TypeConverter fun stringToBigDecimal(value: String?): BigDecimal? = value?.let(::BigDecimal)?.normalise()

    @TypeConverter fun milestoneTypeToString(value: MilestoneType?): String? = value?.name
    @TypeConverter fun stringToMilestoneType(value: String?): MilestoneType? =
        value?.let(MilestoneType::valueOf)

    @TypeConverter fun goalTypeToString(value: GoalType?): String? = value?.name
    @TypeConverter fun stringToGoalType(value: String?): GoalType? = value?.let(GoalType::valueOf)
}
