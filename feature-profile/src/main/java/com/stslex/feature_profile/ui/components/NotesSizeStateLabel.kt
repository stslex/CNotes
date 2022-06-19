package com.stslex.feature_profile.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.stslex.core.ValueState


@Composable
fun NotesSizeStateLabel(
    modifier: Modifier = Modifier,
    labelId: Int,
    notesState: State<ValueState<Int>>
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = LocalContext.current.getString(labelId)
        )

        when (val value = notesState.value) {
            is ValueState.Success ->
                Text(
                    modifier = modifier.align(Alignment.CenterEnd),
                    text = value.data.toString()
                )
            is ValueState.Failure -> Unit
            is ValueState.Loading ->
                LinearProgressIndicator(
                    modifier = Modifier
                        .width(50.dp)
                        .align(Alignment.CenterEnd)
                )
        }
    }
}