package com.example.feature_auth_code.ui

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.feature_auth_code.ui.components.CodeInputField
import com.stslex.core.ValueState

@Composable
fun AuthCodeRoute(
    popBackStack: () -> Unit,
    openProfile: () -> Unit,
    viewModel: AuthCodeViewModel
) {
    AuthCodeScreen(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        popBackStack = popBackStack,
        openProfile = openProfile,
        viewModel = viewModel
    )
}

@Composable
fun AuthCodeScreen(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit,
    openProfile: () -> Unit,
    viewModel: AuthCodeViewModel
) {
    val codeNumber = remember { mutableStateOf("") }
    val activity: Activity = LocalContext.current as Activity
    val stateAuth = viewModel.stateAuth.collectAsState(initial = ValueState.Loading)

    Box(modifier = modifier.fillMaxSize()) {
        when (val authResult = stateAuth.value) {
            is ValueState.Success -> {
                Text(text = "Success")
                openProfile()
            }
            is ValueState.Failure -> {
                Text(text = authResult.exception.message ?: "")

                authResult.exception.fillInStackTrace()
            }
            is ValueState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.TopCenter))
            }
        }

        CodeInputField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            codeNumber = codeNumber
        )

        Button(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.BottomEnd),
            onClick = {
                if (codeNumber.value.length == 6) {
                    viewModel.signIn(activity, codeNumber.value)
                }
            }, content = {
                Text(text = "next")
            })

        Button(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.BottomStart),
            onClick = popBackStack, content = {
                Text(text = "previous")
            })
    }
}