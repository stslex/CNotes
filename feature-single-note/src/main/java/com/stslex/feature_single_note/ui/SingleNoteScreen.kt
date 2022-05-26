package com.stslex.feature_single_note.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteUI
import com.stslex.core_ui.theme.AppTheme

private val noteId = mutableStateOf(0)
private val noteTitle = mutableStateOf("")
private val noteContent = mutableStateOf("")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleNoteScreen(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit,
    viewModel: SingleNoteViewModel = hiltViewModel()
) {
    val noteState: State<ValueState<NoteUI>> = viewModel.note.collectAsState()
    when (val result = noteState.value) {
        is ValueState.Success -> with(result.data) {
            noteId.value = id
            noteTitle.value = title
            noteContent.value = content
        }
        is ValueState.Failure -> Unit
        is ValueState.Loading -> Unit
    }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val noteResult = NoteUI(
                    id = noteId.value,
                    title = noteTitle.value,
                    content = noteContent.value,
                    timestamp = System.currentTimeMillis()
                )
                viewModel.updateNote(noteResult)
                popBackStack()
            }) {
                Text(text = "save")
            }
        }
    ) { padding ->
        SingleNoteContent(modifier = Modifier.padding(padding))
    }
}

@Composable
fun SingleNoteContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            value = noteTitle.value,
            maxLines = 1,
            onValueChange = {
                noteTitle.value = it
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp),
            value = noteContent.value,
            onValueChange = {
                noteContent.value = it
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SingleNotePreview() {
    AppTheme {
        Surface(contentColor = MaterialTheme.colorScheme.background) {
            SingleNoteContent()
        }
    }
}