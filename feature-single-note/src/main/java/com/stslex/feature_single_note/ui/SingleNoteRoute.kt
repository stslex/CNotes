package com.stslex.feature_single_note.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteUI

@Composable
fun SingleNoteRoute(viewModel: SingleNoteViewModel) {
    SingleNoteScreen(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        viewModel = viewModel
    )
}

@Composable
fun SingleNoteScreen(
    modifier: Modifier = Modifier,
    viewModel: SingleNoteViewModel
) {
    val noteState: State<ValueState<NoteUI>> = viewModel.note.collectAsState()

    when (val result = noteState.value) {
        is ValueState.Success -> {
            val note = remember {
                mutableStateOf(result.data)
            }
            SingleNoteContent(
                modifier = modifier,
                noteState = note
            )
            BackHandler(onBack = onBackHandler(note.value, viewModel::updateNote))
        }
        is ValueState.Failure -> Unit
        is ValueState.Loading -> Unit
    }
}

private inline fun onBackHandler(
    noteUI: NoteUI,
    crossinline updateNote: (noteUI: NoteUI) -> Unit
): () -> Unit = {
    if (noteUI.getContent().value.isNotEmpty() || noteUI.getTitle().value.isNotEmpty()) {
        val createdNote = noteUI.copy(timestamp = System.currentTimeMillis())
        updateNote(createdNote)
    }
}