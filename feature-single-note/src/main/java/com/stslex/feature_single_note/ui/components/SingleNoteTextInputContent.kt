package com.stslex.feature_single_note.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.core_model.model.NoteUI

@Composable
fun SingleNoteTextInputContent(
    modifier: Modifier = Modifier,
    note: State<NoteUI>
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp),
        value = note.value.getContent().value,
        onValueChange = (note.value::setContent)
    )
}