package com.example.feature_note_list.ui.note

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun NoteContent(modifier: Modifier = Modifier, content: String) {
    Text(
        modifier = modifier,
        text = content,
        maxLines = 5,
        style = MaterialTheme.typography.bodyLarge,
        overflow = TextOverflow.Ellipsis
    )
}