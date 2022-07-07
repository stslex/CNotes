package com.example.feature_auth_code.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_auth_code.domain.AuthCodeInteractor
import com.stslex.core.ValueState
import com.stslex.core_coroutines.AppDispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthCodeViewModel(
    private val authCodeInteracor: AuthCodeInteractor,
    private val dispatchers: AppDispatchers,
    private val verificationId: String
) : ViewModel() {

    private val _stateAuth: MutableStateFlow<ValueState<Unit>> =
        MutableStateFlow(ValueState.Loading)

    val stateAuth: SharedFlow<ValueState<Unit>>
        get() = _stateAuth.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ValueState.Loading
        )

    fun signIn(activity: Activity, code: String) {
        viewModelScope.launch(dispatchers.io) {
            authCodeInteracor.logIn(verificationId, code, activity).collectLatest {
                _stateAuth.emit(it)
            }
        }
    }
}