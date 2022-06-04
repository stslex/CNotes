package com.stslex.feature_profile.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun ButtonLabel(modifier: Modifier = Modifier, label: String) {
    Text(
        modifier = modifier,
        maxLines = 1,
        text = label,
        style = MaterialTheme.typography.titleMedium,
        overflow = TextOverflow.Ellipsis
    )
}
