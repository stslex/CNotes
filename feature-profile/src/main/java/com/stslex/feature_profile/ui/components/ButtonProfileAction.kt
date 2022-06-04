package com.stslex.feature_profile.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ButtonProfileAction(
    modifier: Modifier = Modifier,
    label: String,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onClick()
            System.out.println("Click:: is Clicked")
        }
    ) {
        ButtonLabel(
            modifier = Modifier.weight(4f),
            label = label
        )
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = imageVector,
            contentDescription = label
        )
    }
}