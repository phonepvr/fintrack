package com.fintrack.ui.help

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Standard `?` icon for top-app-bar action slots. Opens a [HelpSheet]
 * holding the named [Sheet] when tapped.
 */
@Composable
fun HelpIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.AutoMirrored.Filled.HelpOutline,
            contentDescription = "Help",
        )
    }
}

/**
 * Reusable help bottom sheet. Renders title + body + optional bullets +
 * a "Learn more in About" CTA that calls [onLearnMore] with the anchor.
 *
 * Caller controls visibility — typically via a `var showHelp by remember
 * { mutableStateOf(false) }` flag flipped by [HelpIconButton].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpSheet(
    sheet: HelpSheetContent.Sheet,
    onDismiss: () -> Unit,
    onLearnMore: (anchor: String) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
        ) {
            Text(
                sheet.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(12.dp))
            Text(
                sheet.body,
                style = MaterialTheme.typography.bodyMedium,
            )
            if (sheet.bullets.isNotEmpty()) {
                Spacer(Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    for (b in sheet.bullets) {
                        Row(verticalAlignment = Alignment.Top) {
                            Text(
                                "•",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(end = 8.dp),
                            )
                            Text(b, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
            if (sheet.learnMoreAnchor != null) {
                Spacer(Modifier.height(20.dp))
                OutlinedButton(
                    onClick = {
                        // Animate the sheet shut first; About's deep-link scroll
                        // happens on top of a clean back stack.
                        scope.launchAnd(sheetState, onDismiss) {
                            onLearnMore(sheet.learnMoreAnchor)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp),
                    )
                    Text("Learn more in About")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun CoroutineScope.launchAnd(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    afterHide: () -> Unit,
) {
    launch { sheetState.hide() }.invokeOnCompletion {
        // Always dismiss + run the follow-up; if hide() was cancelled because
        // the sheet was already gone, we still want to nav.
        onDismiss()
        afterHide()
    }
}
