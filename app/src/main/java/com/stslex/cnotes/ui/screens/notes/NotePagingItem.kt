package com.stslex.cnotes.ui.screens.notes

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stslex.cnotes.ui.model.NoteUIModel
import com.stslex.cnotes.ui.navigation.NavScreen
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun NotePagingItem(
    note: NoteUIModel,
    navController: NavController,
    selectedNotes: SnapshotStateList<NoteUIModel>
) {
    val colorUnselect = MaterialTheme.colorScheme.surface
    val colorSelect = MaterialTheme.colorScheme.primaryContainer
    val color = animateColorAsState(
        targetValue = if (note.isSelect().value) colorSelect else colorUnselect,
        animationSpec = tween(durationMillis = 250)
    )
    ElevatedCard(
        modifier = Modifier
            .combinedClickable(
                onClick = noteItemClick(
                    note = note,
                    navController = navController,
                    selectedNotes = selectedNotes
                ),
                onLongClick = noteItemLongClick(
                    note = note,
                    selectedNotes = selectedNotes
                )
            )
            .fillMaxWidth()
            .padding(16.dp),
        containerColor = color.value,
        content = noteItemContent(note = note),
    )
}

fun noteItemClick(
    note: NoteUIModel,
    navController: NavController,
    selectedNotes: SnapshotStateList<NoteUIModel>
): () -> Unit = {
    if (selectedNotes.isNotEmpty()) {
        selectItem(note, selectedNotes)
    } else {
        navController.navigate(NavScreen.EditNoteScreen.route)
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