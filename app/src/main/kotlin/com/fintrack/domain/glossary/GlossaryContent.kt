package com.fintrack.domain.glossary

import com.fintrack.data.db.seed.SeedData
import java.util.UUID

/**
 * Canonical glossary copy for the About screen and the help-sheet
 * "Learn more" deep links. Asset-class entries are keyed off the
 * stable seeded UUIDs from [SeedData] so a user-renamed seeded class
 * still resolves to its canonical explanation. User-added classes
 * use [genericAssetClassFallback].
 *
 * Pure-Kotlin object — no Android dependencies — testable on `:app:test`.
 */
object GlossaryContent {

    data class Entry(val title: String, val body: String)

    /** Wealth metrics card. */
    val wealthMetrics: List<Entry> = listOf(
        Entry(
            "Net Worth",
            "Your total assets minus your total liabilities (loans). The single best number to watch over time.",
        ),
        Entry(
            "Total Assets",
            "The sum of the current value of everything you own across all asset classes.",
        ),
        Entry(
            "Total Liabilities",
            "The sum of all outstanding loan balances.",
        ),
        Entry(
            "Invested Amount",
            "How much money you've put in (cumulative), separate from what it's currently worth.",
        ),
        Entry(
            "% of Earnings",
            "Your current net worth as a percentage of your total lifetime earnings — roughly, how much of what you've earned have you actually retained as wealth.",
        ),
        Entry(
            "Gain %",
            "How much your net worth changed since your previous snapshot, as a percentage.",
        ),
    )

    /** Engagement-layer terms (streaks, milestones, goals, aim). */
    val engagementTerms: List<Entry> = listOf(
        Entry(
            "Streak",
            "Consecutive months in which you've added at least one snapshot. The current month is in grace until it ends, so you have all month to log it.",
        ),
        Entry(
            "Milestone",
            "A predefined achievement — your first ₹1 Cr, a doubled net worth, becoming debt-free for the first time. Awarded once and shown in the Wins timeline.",
        ),
        Entry(
            "Goal",
            "A net worth target with a deadline. The app shows your progress and projects whether you're on pace at your current growth rate.",
        ),
        Entry(
            "Aim Allocation",
            "Your target percentage for each asset class. The app compares your actual allocation against this and flags drift.",
        ),
    )

    /** Loan-related terms. */
    val loans: List<Entry> = listOf(
        Entry(
            "Original Amount",
            "The principal sanctioned when the loan was first taken.",
        ),
        Entry(
            "Outstanding",
            "The current loan balance you still owe. You enter this each snapshot.",
        ),
        Entry(
            "EMI",
            "The fixed monthly payment toward the loan.",
        ),
        Entry(
            "Closed",
            "Marking a loan closed sets its end date and stops it from appearing in new snapshots. Historical snapshots still show it.",
        ),
    )

    /** Lookup for seeded asset classes by stable UUID. */
    fun forAssetClassId(id: UUID): Entry? = ASSET_CLASS_ENTRIES[id]

    /** Lookup for seeded sub-buckets by stable UUID. */
    fun forSubBucketId(id: UUID): Entry? = SUB_BUCKET_ENTRIES[id]

    /** Catch-all for user-added asset classes outside the seed set. */
    val genericAssetClassFallback: Entry = Entry(
        title = "User-defined asset class",
        body = "An asset class you added yourself. Track holdings here that don't fit the seeded categories.",
    )

    /** Catch-all for user-added sub-buckets outside the seed set. */
    val genericSubBucketFallback: Entry = Entry(
        title = "User-defined sub-bucket",
        body = "A sub-bucket you added yourself. Use it to group holdings within an asset class.",
    )

    private val ASSET_CLASS_ENTRIES: Map<UUID, Entry> = mapOf(
        SeedData.MARKET_LINKED_ID to Entry(
            "Market Linked",
            "Investments whose value moves with the markets — typically the largest growth driver in a portfolio. Sub-categories include MF (mutual funds), NPS (National Pension System), and direct equity.",
        ),
        SeedData.FIXED_RETURN_ID to Entry(
            "Fixed Return",
            "Investments with predictable, contracted returns. Lower growth, lower risk. Sub-categories include PF, PPF, FDs, Wint Wealth, and Bank balances.",
        ),
        SeedData.CRYPTO_ID to Entry(
            "Crypto",
            "Cryptocurrency holdings. Highest volatility — usually a small slice of a balanced portfolio.",
        ),
    )

    private val SUB_BUCKET_ENTRIES: Map<UUID, Entry> = mapOf(
        SeedData.MF_ID to Entry(
            "MF (Mutual Funds)",
            "Pooled investments managed by a fund house. Equity, debt, hybrid — pick whatever suits your risk profile.",
        ),
        SeedData.NPS_ID to Entry(
            "NPS (National Pension System)",
            "Government-sponsored retirement product. Long lock-in but tax-efficient.",
        ),
        SeedData.STOCKS_ID to Entry(
            "Stocks (Direct Equity)",
            "Shares you hold directly in a demat account, separate from mutual-fund exposure.",
        ),
        SeedData.PF_ID to Entry(
            "PF (Provident Fund)",
            "Employee Provident Fund — auto-deducted from salary, employer-matched. Long-term, low-risk.",
        ),
        SeedData.PPF_ID to Entry(
            "PPF (Public Provident Fund)",
            "15-year government-backed savings scheme with tax-free returns.",
        ),
        SeedData.FD_ID to Entry(
            "FD (Fixed Deposits)",
            "Bank deposits at a contracted interest rate. Safe, predictable, low yield.",
        ),
        SeedData.WINT_WEALTH_ID to Entry(
            "Wint Wealth",
            "Curated corporate-bond platform. Higher coupon than FDs with credit-rating-based risk.",
        ),
        SeedData.BANK_ID to Entry(
            "Bank",
            "Savings-account balances across your banks. Cash-equivalent — close to zero return.",
        ),
        SeedData.CRYPTO_SB_ID to Entry(
            "Crypto",
            "Cryptocurrency holdings on the platform of your choice (e.g. CoinDCX).",
        ),
    )
}
