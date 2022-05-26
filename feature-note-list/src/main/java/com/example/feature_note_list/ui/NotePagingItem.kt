package com.example.feature_note_list.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stslex.core_model.ui.NoteUIModel
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NotePagingItem(
    note: NoteUIModel,
    selectedNotes: SnapshotStateList<NoteUIModel>,
    openSingleNote: (Int) -> Unit
) {
    val colorUnselect = MaterialTheme.colorScheme.onBackground
    val colorSelect = MaterialTheme.colorScheme.inverseSurface
    val color = animateColorAsState(
        targetValue = if (note.isSelect().value) colorSelect else colorUnselect,
        animationSpec = tween(durationMillis = 250)
    )

    ElevatedCard(
        modifier = Modifier
            .combinedClickable(
                onClick = noteItemClick(
                    note = note,
                    openSingleNote = openSingleNote,
                    selectedNotes = selectedNotes
                ),
                onLongClick = noteItemLongClick(
                    note = note,
                    selectedNotes = selectedNotes
                )
            )
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(contentColor = color.value),
        content = noteItemContent(note = note),
    )
}

fun noteItemClick(
    note: NoteUIModel,
    selectedNotes: SnapshotStateList<NoteUIModel>,
    openSingleNote: (Int) -> Unit
): () -> Unit = {
    if (selectedNotes.isNotEmpty()) {
        selectItem(note, selectedNotes)
    } else {
        openSingleNote(note.id())
    }
}

fun noteItemLongClick(
    note: NoteUIModel,
    selectedNotes: SnapshotStateList<NoteUIModel>
): () -> Unit = {
    selectItem(note, selectedNotes)
}

private fun selectItem(
    note: NoteUIModel,
    selectedNotes: SnapshotStateList<NoteUIModel>
) {
    note.setSelect(!note.isSelect().value)
    if (selectedNotes.contains(note)) {
        selectedNotes.remove(note)
    } else selectedNotes.add(note)
}

fun noteItemContent(note: NoteUIModel): @Composable ColumnScope.() -> Unit = {
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