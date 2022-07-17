package com.stslex.feature_auth_phonenumber.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.ButtonNextStep(modifier: Modifier = Modifier, nextStep: () -> Unit) {
    Button(modifier = modifier
        .padding(16.dp)
        .align(Alignment.BottomEnd),
        onClick = nextStep,
        content = {
            Text(text = "next")
        })
}