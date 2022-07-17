package com.stslex.feature_auth_phonenumber.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.feature_auth_phonenumber.core.SignValueState
import com.stslex.feature_auth_phonenumber.domain.AuthPhoneNumberInteractor
import com.stslex.core.AppDispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthPhoneViewModel(
    private val dispatchers: AppDispatchers,
    private val authPhoneNumberInteractor: AuthPhoneNumberInteractor
) : ViewModel() {

    private val _signInState: MutableStateFlow<SignValueState> =
        MutableStateFlow(SignValueState.Loading)
    val signValueState: StateFlow<SignValueState>
        get() = _signInState
            .asStateFlow()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 100),
                initialValue = SignValueState.Loading
            )

    fun signIn(activity: Activity, phoneNumber: String) {
        viewModelScope.launch(dispatchers.io) {
            authPhoneNumberInteractor.login(activity, phoneNumber).collectLatest(_signInState::emit)
        }
    }
}