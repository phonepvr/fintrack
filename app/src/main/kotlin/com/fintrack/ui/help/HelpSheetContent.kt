package com.fintrack.ui.help

/**
 * Anchors used by both the help sheets' "Learn more in About" CTA and
 * AboutScreen's `scrollToSection` deep-link parameter. Keep this list
 * in sync with `AboutScreen.kt`'s `anchorToItemKey()`.
 */
object AboutAnchors {
    const val GLOSSARY_WEALTH = "glossary_wealth"
    const val GLOSSARY_ASSETS = "glossary_assets"
    const val GLOSSARY_ENGAGEMENT = "glossary_engagement"
    const val GLOSSARY_LOANS = "glossary_loans"
    const val PRIVACY = "privacy"
}

/**
 * Static body for each in-context help sheet. Each entry is a single
 * `?` icon's worth of explanation: title, paragraph, optional bullet
 * list, and the About anchor the "Learn more" CTA jumps to.
 *
 * Pure-Kotlin — testable on `:app:test`.
 */
object HelpSheetContent {

    data class Sheet(
        val key: String,
        val title: String,
        val body: String,
        val bullets: List<String> = emptyList(),
        val learnMoreAnchor: String? = null,
    )

    val SNAPSHOT_WHAT_IS: Sheet = Sheet(
        key = "snapshot_what_is",
        title = "What is a snapshot?",
        body = "A snapshot is a single-day capture of your entire financial position — " +
            "what every holding is currently worth and what every loan still owes. " +
            "Most people add one per month.",
        bullets = listOf(
            "Pick the date the snapshot describes (usually today).",
            "Enter today's value of each holding from your broker app, fund statement, or bank.",
            "Enter the outstanding balance of each loan.",
            "Save. Net worth, gain, and streak update automatically.",
        ),
        learnMoreAnchor = AboutAnchors.GLOSSARY_WEALTH,
    )

    val SNAPSHOT_EARNINGS: Sheet = Sheet(
        key = "snapshot_earnings",
        title = "What is \"Earnings (in cr)\"?",
        body = "Your total lifetime earnings — every rupee you've ever taken home from " +
            "salary, freelance, side income, etc. The app divides your current net " +
            "worth by this number to compute % of earnings.",
        bullets = listOf(
            "Enter the cumulative total, not what you earned this month.",
            "Update it each snapshot so the % of earnings metric stays accurate.",
            "If unsure, conservative (lower) estimates are better than overshooting.",
        ),
        learnMoreAnchor = AboutAnchors.GLOSSARY_WEALTH,
    )

    val JOURNEY_STREAKS: Sheet = Sheet(
        key = "journey_streaks",
        title = "Streaks, milestones, and wins",
        body = "Your Wins timeline tells the story of your wealth journey — the " +
            "engagement layer FinTrack uses instead of notifications.",
        bullets = listOf(
            "Streak: consecutive months with at least one snapshot. The current month is in grace until it ends.",
            "Milestone: an achievement awarded once (your first ₹1 Cr, doubled net worth, first time debt-free).",
            "Wins timeline: chronological log of every milestone you've earned.",
        ),
        learnMoreAnchor = AboutAnchors.GLOSSARY_ENGAGEMENT,
    )

    val GOALS_HOW_THEY_WORK: Sheet = Sheet(
        key = "goals_how",
        title = "How goals work",
        body = "Set a net-worth target with a deadline. The app shows your progress, " +
            "your monthly growth rate over the recent past, and projects whether " +
            "you're on pace to hit the target.",
        bullets = listOf(
            "Title and target net worth.",
            "Target date — what you're aiming for.",
            "Edit or delete a goal anytime; goals don't auto-archive when missed.",
        ),
        learnMoreAnchor = AboutAnchors.GLOSSARY_ENGAGEMENT,
    )

    val AIM_WHAT_IS: Sheet = Sheet(
        key = "aim_what_is",
        title = "What is Aim?",
        body = "Your target percentage allocation across asset classes — the portfolio " +
            "shape you'd like to maintain. The app compares your actual allocation " +
            "against this and highlights drift.",
        bullets = listOf(
            "Sliders must sum to 100%.",
            "Drift over a few percent is normal — markets move things around.",
            "Use Aim to plan rebalancing, not as a hard rule.",
        ),
        learnMoreAnchor = AboutAnchors.GLOSSARY_ENGAGEMENT,
    )
}
