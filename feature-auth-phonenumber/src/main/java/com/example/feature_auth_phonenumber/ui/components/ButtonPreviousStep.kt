package com.example.feature_auth_phonenumber.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.ButtonPreviousStep(modifier: Modifier = Modifier, popBackStack: () -> Unit) {
    Button(
        modifier = modifier
            .padding(16.dp)
            .align(Alignment.BottomStart),
        onClick = popBackStack, content = {
            Text(text = "previous")
        })
}