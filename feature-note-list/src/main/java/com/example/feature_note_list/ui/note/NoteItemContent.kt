package com.example.feature_note_list.ui.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.core_model.model.NoteDynamicUI

@Composable
fun NoteItemContent(modifier: Modifier = Modifier, note: NoteDynamicUI) {
    val itemPrimaryModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        NoteTitle(
            modifier = itemPrimaryModifier,
            title = note.title
        )
        NoteContent(
            modifier = itemPrimaryModifier,
            content = note.content
        )
        NoteTimestamp(
            modifier = itemPrimaryModifier,
            timestamp = note.timestamp
        )
    }
}