package com.stslex.feature_single_note.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.stslex.core_model.model.NoteUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleNoteTextInputTitle(
    modifier: Modifier = Modifier,
    note: State<NoteUI>
) {
    TextField(
        modifier = modifier,
        value = note.value.getTitle().value,
        singleLine = true,
        onValueChange = (note.value::setTitle),
        textStyle = MaterialTheme.typography.titleLarge,
        keyboardOptions = KeyboardOptions(autoCorrect = true)
    )
}