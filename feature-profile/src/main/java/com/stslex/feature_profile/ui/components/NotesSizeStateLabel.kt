package com.stslex.feature_profile.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.core.ValueState


@Composable
fun NotesSizeStateLabel(
    modifier: Modifier = Modifier,
    label: String,
    notesState: State<ValueState<Int>>
) {
    when (val value = notesState.value) {
        is ValueState.Success -> {
            Text(
                modifier = modifier.padding(16.dp),
                text = "$label: ${value.data}"
            )
        }
        is ValueState.Failure -> Unit
        is ValueState.Loading -> Unit
    }
}