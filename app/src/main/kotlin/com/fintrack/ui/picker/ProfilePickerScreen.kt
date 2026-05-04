package com.fintrack.ui.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row as LayoutRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.data.db.entities.UserEntity
import com.fintrack.ui.onboarding.parseHex
import java.util.UUID

@Composable
fun ProfilePickerRoute(
    onUserPicked: (UUID) -> Unit,
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
                title = { Text("Choose profile") },
            )
        },
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 140.dp),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            items(users, key = { it.id }) { user ->
                ProfileTile(
                    user = user,
                    onTap = { onUserPicked(user.id) },
                    onEdit = { editTarget = user },
                    onDelete = { deleteTarget = user },
                    canDelete = users.size > 1,
                )
            }
            item {
                AddProfileTile(onClick = { addOpen = true })
            }
        }
    }

    if (addOpen) {
        ProfileEditorDialog(
            initialName = "",
            initialColorHex = DefaultColorPalette.first(),
            title = "Add profile",
            confirmLabel = "Create",
            onDismiss = { addOpen = false },
            onConfirm = { name, color ->
                viewModel.createUser(name, color) { id ->
                    addOpen = false
                    onUserPicked(id)
                }
            },
        )
    }

    val edit = editTarget
    if (edit != null) {
        ProfileEditorDialog(
            initialName = edit.name,
            initialColorHex = edit.colorHex,
            title = "Edit profile",
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

@Composable
private fun ProfileTile(
    user: UserEntity,
    onTap: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    canDelete: Boolean,
) {
    var menuOpen by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
            .combinedClickable(
                onClick = onTap,
                onLongClick = { menuOpen = true },
            )
            .padding(12.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(parseHex(user.colorHex)),
            )
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
        DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = {
                    menuOpen = false
                    onEdit()
                },
            )
            DropdownMenuItem(
                text = { Text("Delete") },
                enabled = canDelete,
                onClick = {
                    menuOpen = false
                    onDelete()
                },
            )
        }
    }
}

@Composable
private fun AddProfileTile(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                shape = RoundedCornerShape(12.dp),
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Filled.Add, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(4.dp))
            Text("Add profile", color = MaterialTheme.colorScheme.primary)
        }
    }
}

private val DefaultColorPalette = listOf(
    "#1976D2",
    "#E91E63",
    "#43A047",
    "#F4511E",
    "#8E24AA",
    "#00897B",
    "#5E35B1",
    "#3949AB",
)

private const val MAX_NAME_LENGTH = 40

@Composable
private fun ProfileEditorDialog(
    initialName: String,
    initialColorHex: String,
    title: String,
    confirmLabel: String,
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit,
) {
    var name by rememberSaveable(initialName) { mutableStateOf(initialName) }
    var colorHex by rememberSaveable(initialColorHex) { mutableStateOf(initialColorHex) }
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
                LayoutRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    DefaultColorPalette.forEach { hex ->
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(parseHex(hex))
                                .border(
                                    width = if (hex == colorHex) 3.dp else 1.dp,
                                    color = if (hex == colorHex) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.outline,
                                    shape = CircleShape,
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
    var confirmText by rememberSaveable { mutableStateOf("") }
    val matches = confirmText == user.name
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Delete profile?") },
        text = {
            Column {
                Text(
                    text = "All snapshots and aim percentages for ${user.name} will be permanently deleted.",
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Type \"${user.name}\" to confirm.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmText,
                    onValueChange = { confirmText = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm, enabled = matches) {
                Text("Delete")
            }
        },
        dismissButton = { TextButton(onClick = onCancel) { Text("Cancel") } },
    )
}
