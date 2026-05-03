package com.fintrack.domain.model

/**
 * The four asset classes are global and immutable. Defaults are seeded per-user
 * on user creation; users adjust their own aim percentages independently.
 */
enum class AssetClass(
    val displayName: String,
    val riskLabel: String,
    val defaultAimPct: Int,
) {
    MF_NPS("MF + NPS", "Moderate", 55),
    EQUITY("Equity", "Aggressive", 15),
    FIXED_RETURN("Fixed Return", "Safe", 25),
    CRYPTO("Crypto", "Very Aggressive", 5),
    ;

    companion object {
        init {
            require(entries.sumOf { it.defaultAimPct } == 100) {
                "Default aim percentages must sum to 100"
            }
        }
    }
}
