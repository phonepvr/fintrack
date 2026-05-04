package com.fintrack.security

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

/**
 * Privacy-aware money TextField wrapper.
 *
 * - autoCorrect = false  → IME doesn't surface typed digits as suggestions
 * - keyboardType = Decimal → numeric pad without next-word predictions
 * - capitalization = None
 * - imeAction = Done (no auto-Next that scrapes screen state)
 *
 * The Compose UI autofill semantics API (ContentType) is unavailable in
 * Compose UI 1.7.x; the numeric keyboard already prevents most IME
 * leaks for money fields. Revisit when Compose 1.8 lands.
 *
 * All money TextFields in the app should use this wrapper.
 */
@Composable
fun MoneyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        enabled = enabled,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done,
        ),
        modifier = modifier,
    )
}
