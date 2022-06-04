package com.stslex.feature_profile.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileActionButtons(
    modifier: Modifier = Modifier,
    actionDownload: () -> Unit,
    actionUpload: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {

        ButtonProfileAction(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            label = "Download",
            imageVector = Icons.Filled.KeyboardArrowDown,
            onClick = actionDownload
        )

        ButtonProfileAction(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            label = "Upload",
            imageVector = Icons.Filled.KeyboardArrowUp,
            onClick = actionUpload
        )
    }
}