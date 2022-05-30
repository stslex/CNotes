package com.example.feature_auth_phonenumber.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_auth_phonenumber.core.SignValueState
import com.example.feature_auth_phonenumber.ui.use_cases.AuthPhoneNumberUseCase
import com.stslex.core_coroutines.AppDispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthPhoneViewModel(
    private val dispatchers: AppDispatchers,
    private val authPhoneNumberUseCase: AuthPhoneNumberUseCase
) : ViewModel() {

    private val _signInState: MutableSharedFlow<SignValueState> = MutableSharedFlow(0)
    val signValueState: SharedFlow<SignValueState>
        get() = _signInState.asSharedFlow()

    fun signIn(activity: Activity, phoneNumber: String) {
        viewModelScope.launch(dispatchers.io) {
            authPhoneNumberUseCase.signIn(activity, phoneNumber)
                .collectLatest(_signInState::emit)
        }
    }
}