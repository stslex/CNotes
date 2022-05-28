package com.stslex.feature_single_note.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteUI
import com.stslex.feature_single_note.ui.components.SingleNoteSaveFab

@Composable
fun SingleNoteRoute(
    popBackStack: () -> Unit,
    viewModel: SingleNoteViewModel
) {
    SingleNoteScreen(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        popBackStack = popBackStack,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleNoteScreen(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit,
    viewModel: SingleNoteViewModel
) {
    val noteState: State<ValueState<NoteUI>> = viewModel.note.collectAsState()
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            SingleNoteSaveFab(
                onClickSave = noteState.onClickSave(viewModel::updateNote, popBackStack)
            )
        }
    ) { paddingValues ->
        SingleNoteContent(
            modifier = Modifier.padding(paddingValues),
            valueState = noteState
        )
    }
}

fun State<ValueState<NoteUI>>.onClickSave(
    updateNote: (NoteUI) -> Unit,
    popBackStack: () -> Unit
): () -> Unit = {
    when (val result = value) {
        is ValueState.Success -> with(result.data) {
            if (getContent().value.isNotEmpty() || getTitle().value.isNotEmpty()) {
                updateNote(result.data.copy(timestamp = System.currentTimeMillis()))
                popBackStack()
            }
        }
        is ValueState.Failure -> Unit
        is ValueState.Loading -> Unit
    }
}