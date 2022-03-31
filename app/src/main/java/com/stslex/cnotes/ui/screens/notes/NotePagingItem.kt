package com.stslex.cnotes.ui.screens.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stslex.cnotes.ui.model.NoteUIModel
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalMaterial3Api
@Composable
fun NotePagingItem(note: NoteUIModel) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            NoteTitle(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                title = note.title()
            )
            NoteContent(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                content = note.content()
            )
            NoteTimestamp(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                timestamp = note.timestamp()
            )
        }
    }
}

@Composable
fun NoteTitle(modifier: Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 1,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun NoteContent(modifier: Modifier, content: String) {
    Text(
        modifier = modifier,
        text = content.take(100),
        maxLines = 5,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
fun NoteTimestamp(modifier: Modifier, timestamp: Long) {
    val locale = Locale.getDefault()
    val time = SimpleDateFormat("kk.mm", locale).format(timestamp)
    Text(
        modifier = modifier,
        text = time,
        maxLines = 5,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.End
    )
}