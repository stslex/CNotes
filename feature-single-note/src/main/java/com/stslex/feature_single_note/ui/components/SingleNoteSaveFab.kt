package com.stslex.feature_single_note.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stslex.core_ui.R.string

@Composable
fun SingleNoteSaveFab(
    modifier: Modifier = Modifier,
    onClickSave: () -> Unit
) {
    FloatingActionButton(
        onClick = onClickSave,
        shape = Shapes.Full.copy(topEnd = CornerSize(10.dp))
    ) {
        Text(
            modifier = modifier.padding(20.dp),
            text = stringResource(id = string.label_save)
        )
    }
}