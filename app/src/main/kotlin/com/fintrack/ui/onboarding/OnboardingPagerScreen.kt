package com.fintrack.ui.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockPerson
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Four-card swipeable Material 3 pager shown either:
 *
 * - on first launch (and v3-upgrade) via the AppState `NeedsOnboarding`
 *   gate — `isReplay = false`, [onComplete] flips `hasCompletedOnboarding`
 *   to `true`. System-back on the last card pops a confirmation dialog.
 * - from About → "Replay onboarding" — `isReplay = true`, [onDismissReplay]
 *   pops the back stack. The flag is NOT touched.
 *
 * Material iconography only (no emoji / Lottie / illustrations) — pulled
 * from `material-icons-extended`.
 */
@Composable
fun OnboardingPagerRoute(
    onComplete: () -> Unit,
    isReplay: Boolean = false,
    onDismissReplay: () -> Unit = {},
) {
    OnboardingPager(
        cards = onboardingCards,
        isReplay = isReplay,
        onComplete = onComplete,
        onDismissReplay = onDismissReplay,
    )
}

private data class OnboardingCard(
    val headline: String,
    val body: String,
    val icon: ImageVector,
)

private val onboardingCards: List<OnboardingCard> = listOf(
    OnboardingCard(
        headline = "A private place for your wealth",
        body = "FinTrack tracks your portfolio entirely on this device. " +
            "No accounts, no internet, no cloud backups. Your data is encrypted " +
            "and only you can unlock it with your fingerprint or face.",
        icon = Icons.Filled.Lock,
    ),
    OnboardingCard(
        headline = "Capture your wealth in minutes",
        body = "Once a month, open the app and update the current value of each " +
            "holding — mutual funds, stocks, fixed deposits, crypto, loans. " +
            "FinTrack does the math: total assets, liabilities, net worth, " +
            "gain over time.",
        icon = Icons.Filled.Edit,
    ),
    OnboardingCard(
        headline = "See your money grow",
        body = "Every entry builds your timeline. Track net worth across months, " +
            "watch your investments versus your debts, hit milestones, and stay " +
            "on streak. Your \"Journey & Goals\" tab tells the story.",
        icon = Icons.Filled.TrendingUp,
    ),
    OnboardingCard(
        headline = "Built for trust, not engagement loops",
        body = "No notifications. No analytics. No ads. The app stays out of your " +
            "way and keeps your financial life private. You can review the full " +
            "privacy promise anytime in About.",
        icon = Icons.Filled.LockPerson,
    ),
)

@Composable
private fun OnboardingPager(
    cards: List<OnboardingCard>,
    isReplay: Boolean,
    onComplete: () -> Unit,
    onDismissReplay: () -> Unit,
) {
    val pageCount = cards.size
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val scope = rememberCoroutineScope()
    var showSkipConfirm by remember { mutableStateOf(false) }

    val finishCta: () -> Unit = if (isReplay) onDismissReplay else onComplete
    val isLastPage = pagerState.currentPage == pageCount - 1

    // System back on the last card (first-launch only) confirms before
    // dismissing — for any other page we step backward in the pager.
    BackHandler(enabled = pagerState.currentPage > 0) {
        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
    }
    BackHandler(enabled = isLastPage && !isReplay) {
        showSkipConfirm = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text(if (isReplay) "Welcome back" else "Welcome to FinTrack") },
                actions = {
                    if (!isLastPage) {
                        TextButton(onClick = {
                            scope.launch { pagerState.animateScrollToPage(pageCount - 1) }
                        }) { Text("Skip") }
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f).fillMaxWidth(),
            ) { pageIndex ->
                OnboardingCardView(card = cards[pageIndex])
            }
            PagerIndicator(
                pageCount = pageCount,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
            )
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
            ) {
                if (isLastPage) {
                    Button(
                        onClick = finishCta,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(if (isReplay) "Done" else "Get started")
                    }
                } else {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(onClick = {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                        }) { Text("Next") }
                    }
                }
            }
        }
    }

    if (showSkipConfirm) {
        AlertDialog(
            onDismissRequest = { showSkipConfirm = false },
            title = { Text("Skip onboarding?") },
            text = { Text("You can replay it anytime from About.") },
            confirmButton = {
                TextButton(onClick = {
                    showSkipConfirm = false
                    onComplete()
                }) { Text("Skip") }
            },
            dismissButton = {
                TextButton(onClick = { showSkipConfirm = false }) { Text("Cancel") }
            },
        )
    }
}

@Composable
private fun OnboardingCardView(card: OnboardingCard) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = card.icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(96.dp),
        )
        Spacer(Modifier.height(32.dp))
        Text(
            card.headline,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(16.dp))
        Text(
            card.body,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0 until pageCount) {
            val selected = i == currentPage
            val targetWidth by animateFloatAsState(
                targetValue = if (selected) 24f else 8f,
                label = "indicator-width-$i",
            )
            val color = if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
            Box(
                modifier = Modifier
                    .size(width = targetWidth.dp, height = 8.dp)
                    .padding(horizontal = 2.dp)
                    .clip(CircleShape)
                    .background(color),
            )
            Spacer(Modifier.width(4.dp))
        }
    }
}
