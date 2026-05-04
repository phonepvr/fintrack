package com.fintrack.domain.model

/**
 * Predefined milestone categories. Per-user, awarded at most once except
 * BACK_FROM_BREAK which can re-trigger after every streak break.
 */
enum class MilestoneType(val displayName: String, val isRepeatable: Boolean = false) {
    FIRST_SNAPSHOT("First snapshot"),
    TWELVE_MONTHS_TRACKED("12 months tracked"),
    THIRTY_SIX_MONTHS_TRACKED("36 months tracked"),
    NET_WORTH_50L("₹50 L net worth"),
    NET_WORTH_1CR("₹1 Cr net worth"),
    NET_WORTH_2CR("₹2 Cr net worth"),
    NET_WORTH_5CR("₹5 Cr net worth"),
    NET_WORTH_10CR("₹10 Cr net worth"),
    DOUBLED_NET_WORTH("Doubled your net worth"),
    TRIPLED_NET_WORTH("Tripled your net worth"),
    YOY_GROWTH_50_PCT("50% growth in a rolling 12 months"),
    DEBT_FREE("Debt-free"),
    BACK_FROM_BREAK("Back from a break", isRepeatable = true),
}
