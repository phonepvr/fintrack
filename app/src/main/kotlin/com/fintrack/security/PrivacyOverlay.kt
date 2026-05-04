package com.fintrack.security

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

/**
 * Defense-in-depth privacy mask.
 *
 * FLAG_SECURE on the activity already prevents recents-thumbnail and
 * screenshot capture, but a few OEM skins have shipped bugs where the
 * thumbnail captures briefly before FLAG_SECURE is honoured. This overlay
 * paints a solid surface over the UI between ON_PAUSE and ON_RESUME so
 * any captured frame contains nothing useful.
 *
 * Wrap the app content with [PrivacyOverlay] near the root of the Compose
 * tree.
 */
@Composable
fun PrivacyOverlay(content: @Composable () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var masked by remember { mutableStateOf(false) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> masked = true
                Lifecycle.Event.ON_RESUME -> masked = false
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        content()
        if (masked) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface),
            )
        }
    }
}
