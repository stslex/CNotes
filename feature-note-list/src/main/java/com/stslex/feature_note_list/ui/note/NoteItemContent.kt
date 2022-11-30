package com.stslex.feature_note_list.ui.note

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.feature_note_list.ui.utils.ColorExt.textColor

@Composable
fun NoteItemContent(
    modifier: Modifier = Modifier,
    note: NoteDynamicUI,
) {
    val color = note.textColor()
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
            title = note.title,
            color = color
        )
        NoteContent(
            modifier = itemPrimaryModifier,
            content = note.content,
            color = color
        )
        NoteTimestamp(
            modifier = itemPrimaryModifier,
            timestamp = note.timestamp,
            color = color
        )
    }
}

