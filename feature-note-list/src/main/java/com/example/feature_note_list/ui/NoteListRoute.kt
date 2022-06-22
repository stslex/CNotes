package com.example.feature_note_list.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteListRoute(
    openSingleNote: (Int) -> Unit,
    openProfile: () -> Unit,
    openAuthPhoneNumber: () -> Unit
) {
    NotesScreen(
        openSingleNote = openSingleNote,
        openProfile = openProfile,
        openAuthPhoneNumber = openAuthPhoneNumber,
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
    )
}