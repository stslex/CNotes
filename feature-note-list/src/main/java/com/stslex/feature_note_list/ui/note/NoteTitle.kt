package com.stslex.feature_note_list.ui.note

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun NoteTitle(
    modifier: Modifier = Modifier,
    title: String,
    color: State<Color>
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 1,
        style = MaterialTheme.typography.titleLarge,
        overflow = TextOverflow.Ellipsis,
        color = color.value
    )
}