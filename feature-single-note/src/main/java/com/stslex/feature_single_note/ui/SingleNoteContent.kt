package com.stslex.feature_single_note.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.core_model.model.NoteUI
import com.stslex.core_ui.theme.AppTheme
import com.stslex.feature_single_note.ui.components.SingleNoteTextInputContent
import com.stslex.feature_single_note.ui.components.SingleNoteTextInputTitle

@Composable
fun SingleNoteContent(
    modifier: Modifier = Modifier,
    noteState: State<NoteUI>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        SingleNoteTextInputTitle(
            modifier = Modifier.fillMaxWidth(),
            note = noteState
        )
        SingleNoteTextInputContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            note = noteState
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SingleNotePreview() {
    AppTheme {
        Surface(contentColor = MaterialTheme.colorScheme.background) {
            val valueState = remember {
                mutableStateOf(
                    NoteUI(
                        id = 0,
                        title = mutableStateOf("Title"),
                        content = mutableStateOf("Content"),
                        timestamp = System.currentTimeMillis()
                    )
                )
            }
            SingleNoteContent(noteState = valueState)
        }
    }
}