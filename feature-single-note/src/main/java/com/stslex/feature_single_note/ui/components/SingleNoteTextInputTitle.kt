package com.stslex.feature_single_note.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.core_model.model.NoteUI

@Composable
fun SingleNoteTextInputTitle(
    modifier: Modifier = Modifier,
    note: State<NoteUI>
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = note.value.getTitle().value,
        maxLines = 1,
        onValueChange = (note.value::setTitle)
    )
}