package com.stslex.feature_auth_code.ui

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.stslex.feature_auth_code.ui.components.CodeInputField
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
    val stateAuth by viewModel.stateAuth.collectAsState(initial = ValueState.Loading)
    var clicked by remember { mutableStateOf(false) }
    Box(modifier = modifier.fillMaxSize()) {
        if (clicked) {
            when (val authResult = stateAuth) {
                is ValueState.Success -> result(openProfile = openProfile).invoke(this)
                is ValueState.Failure -> result(valueState = authResult).invoke(this)
                is ValueState.Loading -> result().invoke(this)
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
                    clicked = true
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

private fun result(
    modifier: Modifier = Modifier,
    valueState: ValueState.Failure
): @Composable BoxScope.() -> Unit = {
    Snackbar(
        modifier = modifier
            .padding(top = 32.dp)
            .align(Alignment.TopCenter)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = valueState.exception.message ?: ""
        )
    }
    valueState.exception.fillInStackTrace()
}

private fun result(
    modifier: Modifier = Modifier
): @Composable BoxScope.() -> Unit = {
    CircularProgressIndicator(
        modifier = modifier
            .padding(top = 32.dp)
            .align(Alignment.TopCenter)
    )
}

private fun result(
    modifier: Modifier = Modifier,
    openProfile: () -> Unit
): @Composable BoxScope.() -> Unit = {
    Snackbar(
        modifier = modifier
            .padding(top = 32.dp)
            .align(Alignment.TopCenter),
        action = {
            openProfile()
        }
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Success"
        )
    }
}