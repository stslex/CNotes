package com.example.feature_note_list.ui.note

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.core_model.model.NoteDynamicUI


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NotePagingItem(
    note: NoteDynamicUI,
    selectedNotes: SnapshotStateList<NoteDynamicUI>,
    openSingleNote: (Int) -> Unit,
    isButtonVisible: MutableState<Boolean>
) {
    val colorUnselect = MaterialTheme.colorScheme.surface
    val colorSelect = MaterialTheme.colorScheme.primaryContainer
    val color = animateColorAsState(
        targetValue = if (note.isSelect().value) colorSelect else colorUnselect,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 50,
            easing = LinearOutSlowInEasing
        )
    )
    OutlinedCard(
        modifier = Modifier
            .combinedClickable(
                onClick = noteItemClick(
                    note = note,
                    openSingleNote = openSingleNote,
                    selectedNotes = selectedNotes,
                    isButtonVisible = isButtonVisible
                ),
                onLongClick = noteItemLongClick(
                    note = note,
                    selectedNotes = selectedNotes
                )
            )
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = color.value,
            containerColor = color.value,
            disabledContainerColor = color.value
        ),
        content = { NoteItemContent(note = note) }
    )
}

fun noteItemClick(
    note: NoteDynamicUI,
    selectedNotes: SnapshotStateList<NoteDynamicUI>,
    openSingleNote: (Int) -> Unit,
    isButtonVisible: MutableState<Boolean>
): () -> Unit = {
    if (selectedNotes.isNotEmpty()) {
        selectItem(note, selectedNotes)
    } else {
        isButtonVisible.value = false
        openSingleNote(note.id)
    }
}

fun noteItemLongClick(
    note: NoteDynamicUI,
    selectedNotes: SnapshotStateList<NoteDynamicUI>
): () -> Unit = {
    selectItem(note, selectedNotes)
}

private fun selectItem(
    note: NoteDynamicUI,
    selectedNotes: SnapshotStateList<NoteDynamicUI>
) {
    note.setSelect(!note.isSelect().value)
    if (selectedNotes.contains(note)) {
        selectedNotes.remove(note)
    } else selectedNotes.add(note)
}