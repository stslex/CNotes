package com.example.feature_auth_phonenumber.ui

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_auth_phonenumber.core.SignValueState
import com.example.feature_auth_phonenumber.navigation.PhoneNumberDestination
import com.google.firebase.FirebaseApp
import com.stslex.core_ui.theme.AppTheme
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun PhoneNumberRoute(
    popBackStack: () -> Unit,
    openCodeInput: (String) -> Unit,
    openUserProfile: () -> Unit
) {
    if (FirebaseApp.getApps(LocalContext.current).isEmpty()) {
        FirebaseApp.initializeApp(LocalContext.current, get(), PhoneNumberDestination.destination)
    }
    PhoneNumberScreen(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        popBackStack = popBackStack,
        openCodeInput = openCodeInput,
        openUserProfile = openUserProfile
    )
}

@Composable
fun PhoneNumberScreen(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit,
    openCodeInput: (String) -> Unit,
    openUserProfile: () -> Unit,
    viewModel: AuthPhoneViewModel = getViewModel()
) {
    val activity: Activity = LocalContext.current as Activity
    val phoneNumber = remember { mutableStateOf("+7") }
    val isError = remember { mutableStateOf(false) }
    val phoneSize = remember { mutableStateOf(12) }
    phoneSize.value =
        if (phoneNumber.value.isNotEmpty() && phoneNumber.value.first() == '+') 12 else 11
    val signInState = viewModel.signValueState.collectAsState(null)
    signInState.value?.collector(openCodeInput, openUserProfile)
    Box(modifier = modifier.fillMaxSize()) {
        PhoneNumberField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            phoneNumber = phoneNumber,
            isError = isError,
            phoneSize = phoneSize
        )

        Button(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.BottomEnd),
            onClick = {
                if (phoneNumber.value.length != phoneSize.value) {
                    isError.value = true
                } else {
                    viewModel.signIn(activity, phoneNumber.value)
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

inline fun SignValueState.collector(
    crossinline openCodeInput: (String) -> Unit,
    crossinline openUserProfile: () -> Unit
) {
    when (this) {
        is SignValueState.Success -> openUserProfile()
        is SignValueState.SendCode -> openCodeInput(id)
        is SignValueState.Failure -> Unit
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PhoneNumberScreenPreview() {
    AppTheme {
        Surface {
            PhoneNumberScreen(Modifier, {}, {}, {})
        }
    }
}
