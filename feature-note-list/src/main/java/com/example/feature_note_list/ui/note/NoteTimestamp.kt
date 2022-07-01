package com.example.feature_note_list.ui.note

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun NoteTimestamp(modifier: Modifier = Modifier, timestamp: String) {
    Text(
        modifier = modifier,
        text = timestamp,
        maxLines = 5,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.End
    )
}