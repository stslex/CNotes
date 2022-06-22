package com.example.feature_auth_phonenumber.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.feature_auth_phonenumber.core.SignValueState
import com.example.feature_auth_phonenumber.ui.components.ButtonNextStep
import com.example.feature_auth_phonenumber.ui.components.ButtonPreviousStep
import com.google.firebase.FirebaseApp
import com.stslex.core_navigation.PhoneNumberDestination
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

    val signInValue = viewModel.signValueState.collectAsState(SignValueState.Loading)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        PhoneNumberField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            phoneNumber = phoneNumber,
            isError = isError,
            phoneSize = phoneSize
        )

        ButtonNextStep {
            if (phoneNumber.value.length != phoneSize.value) {
                isError.value = true
            } else {
                viewModel.signIn(activity, phoneNumber.value)
                signInValue.collector(openCodeInput, openUserProfile)
            }
        }

        ButtonPreviousStep(popBackStack = popBackStack)
    }
}

inline fun State<SignValueState>.collector(
    crossinline openCodeInput: (String) -> Unit,
    crossinline openUserProfile: () -> Unit
) {
    when (val result = value) {
        is SignValueState.Success -> openUserProfile()
        is SignValueState.SendCode -> openCodeInput(result.id)
        is SignValueState.Failure -> Unit
        is SignValueState.Loading -> Unit
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
