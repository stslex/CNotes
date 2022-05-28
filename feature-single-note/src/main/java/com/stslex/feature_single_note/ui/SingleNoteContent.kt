package com.stslex.feature_single_note.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteUI
import com.stslex.core_ui.theme.AppTheme
import com.stslex.feature_single_note.ui.components.SingleNoteTextInputContent
import com.stslex.feature_single_note.ui.components.SingleNoteTextInputTitle

@Composable
fun SingleNoteContent(
    modifier: Modifier = Modifier,
    valueState: State<ValueState<NoteUI>>
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        when (val result = valueState.value) {
            is ValueState.Success -> {
                val note = remember {
                    mutableStateOf(result.data)
                }
                SingleNoteTextInputTitle(note = note)
                SingleNoteTextInputContent(note = note)
            }
            is ValueState.Failure -> Unit
            is ValueState.Loading -> Unit
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SingleNotePreview() {
    AppTheme {
        Surface(contentColor = MaterialTheme.colorScheme.background) {

        }
    }
}