package com.fintrack.ui.settings.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.data.db.entities.UserEntity
import com.fintrack.ui.onboarding.parseHex
import com.fintrack.ui.picker.ProfilePickerViewModel
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape as Circle

@Composable
fun ManageUsersRoute(
    onBack: () -> Unit,
    viewModel: ProfilePickerViewModel = hiltViewModel(),
) {
    val users by viewModel.users.collectAsState()
    var addOpen by rememberSaveable { mutableStateOf(false) }
    var editTarget by remember { mutableStateOf<UserEntity?>(null) }
    var deleteTarget by remember { mutableStateOf<UserEntity?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text("Manage users") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { addOpen = true },
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text("Add user") },
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(bottom = 96.dp),
        ) {
            items(users, key = { it.id }) { user ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(parseHex(user.colorHex)),
                    )
                    Spacer(Modifier.size(12.dp))
                    Text(user.name, modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium)
                    IconButton(onClick = { editTarget = user }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    IconButton(onClick = { deleteTarget = user }, enabled = users.size > 1) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete",
                            tint = if (users.size > 1) MaterialTheme.colorScheme.error
                                   else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
                    }
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            }
        }
    }

    if (addOpen) {
        UserEditorDialog(
            initialName = "",
            initialColor = ColorPalette.first(),
            title = "Add user",
            confirmLabel = "Create",
            onDismiss = { addOpen = false },
            onConfirm = { name, color ->
                viewModel.createUser(name, color) { addOpen = false }
            },
        )
    }
    val edit = editTarget
    if (edit != null) {
        UserEditorDialog(
            initialName = edit.name,
            initialColor = edit.colorHex,
            title = "Edit user",
            confirmLabel = "Save",
            onDismiss = { editTarget = null },
            onConfirm = { name, color ->
                viewModel.renameUser(edit.id, name, color)
                editTarget = null
            },
        )
    }
    val del = deleteTarget
    if (del != null) {
        DeleteUserDialog(
            user = del,
            onCancel = { deleteTarget = null },
            onConfirm = {
                viewModel.deleteUser(del.id)
                deleteTarget = null
            },
        )
    }
}

private val ColorPalette = listOf(
    "#1976D2", "#E91E63", "#43A047", "#F4511E",
    "#8E24AA", "#00897B", "#5E35B1", "#3949AB",
)

private const val MAX_NAME_LENGTH = 40

@Composable
private fun UserEditorDialog(
    initialName: String,
    initialColor: String,
    title: String,
    confirmLabel: String,
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit,
) {
    var name by rememberSaveable(initialName) { mutableStateOf(initialName) }
    var colorHex by rememberSaveable(initialColor) { mutableStateOf(initialColor) }
    val nameError = when {
        name.isBlank() -> "Name is required"
        name.length > MAX_NAME_LENGTH -> "Keep it under $MAX_NAME_LENGTH characters"
        else -> null
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { if (it.length <= MAX_NAME_LENGTH * 2) name = it },
                    label = { Text("Name") },
                    singleLine = true,
                    isError = nameError != null && name.isNotEmpty(),
                    supportingText = nameError?.takeIf { name.isNotEmpty() }?.let { { Text(it) } },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(12.dp))
                Text("Colour", style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    ColorPalette.forEach { hex ->
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(Circle)
                                .background(parseHex(hex))
                                .border(
                                    width = if (hex == colorHex) 3.dp else 1.dp,
                                    color = if (hex == colorHex) MaterialTheme.colorScheme.primary
                                            else MaterialTheme.colorScheme.outline,
                                    shape = Circle,
                                )
                                .clickable { colorHex = hex },
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { if (nameError == null) onConfirm(name.trim(), colorHex) },
                enabled = nameError == null,
            ) { Text(confirmLabel) }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}

@Composable
private fun DeleteUserDialog(
    user: UserEntity,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
) {
    var typed by rememberSaveable { mutableStateOf("") }
    val matches = typed == user.name
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Delete user?") },
        text = {
            Column {
                Text("All snapshots and aim percentages for ${user.name} will be permanently deleted.")
                Spacer(Modifier.height(12.dp))
                Text(
                    "Type \"${user.name}\" to confirm.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = typed, onValueChange = { typed = it },
                    singleLine = true, modifier = Modifier.fillMaxWidth())
            }
        },
        confirmButton = { TextButton(onClick = onConfirm, enabled = matches) { Text("Delete") } },
        dismissButton = { TextButton(onClick = onCancel) { Text("Cancel") } },
    )
}
