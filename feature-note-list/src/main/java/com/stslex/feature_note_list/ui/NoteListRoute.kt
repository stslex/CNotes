package com.stslex.feature_note_list.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteListRoute() {
    NotesScreen(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
    )
}