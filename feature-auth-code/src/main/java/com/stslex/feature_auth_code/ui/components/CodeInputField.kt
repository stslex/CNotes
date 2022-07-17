package com.stslex.feature_auth_code.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CodeInputField(
    modifier: Modifier = Modifier,
    codeNumber: MutableState<String>
) {
    val enabled = remember { mutableStateOf(true) }
    OutlinedTextField(modifier = modifier.padding(16.dp),
        value = codeNumber.value,
        singleLine = true,
        enabled = enabled.value,
        textStyle = MaterialTheme.typography.headlineMedium,
        label = { Text(text = "Code") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        trailingIcon = {
            IconButton(modifier = Modifier.padding(4.dp), onClick = {
                codeNumber.value = ""
            }) {
                Icon(
                    imageVector = Icons.Filled.Clear, contentDescription = "clear all"
                )
            }
        },
        onValueChange = {
            if (it.length <= 6) {
                codeNumber.value = it
            }
        })
}