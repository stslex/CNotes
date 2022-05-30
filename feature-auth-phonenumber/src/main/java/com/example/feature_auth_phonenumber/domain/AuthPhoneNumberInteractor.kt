package com.example.feature_auth_phonenumber.domain

import android.app.Activity
import com.example.feature_auth_phonenumber.core.SignValueState
import com.example.feature_auth_phonenumber.data.AuthPhoneNumberRepository
import com.example.feature_auth_phonenumber.ui.use_cases.AuthPhoneNumberUseCase
import com.stslex.core.ValueState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

interface AuthPhoneNumberInteractor {

    suspend fun login(activity: Activity, phoneNumber: String): Flow<SignValueState>

    class Base(
        private val authPhoneNumberUseCase: AuthPhoneNumberUseCase,
        private val authPhoneNumberRepository: AuthPhoneNumberRepository
    ) : AuthPhoneNumberInteractor {

        override suspend fun login(
            activity: Activity,
            phoneNumber: String
        ): Flow<SignValueState> = flow {
            authPhoneNumberUseCase.signIn(activity, phoneNumber).collectLatest { logInResult ->
                if (logInResult is SignValueState.Success) {
                    authPhoneNumberRepository.saveUser().collectLatest { valueState ->
                        when (valueState) {
                            is ValueState.Success -> emit(logInResult)
                            is ValueState.Failure -> emit(SignValueState.Failure(valueState.exception))
                            else -> Unit
                        }
                    }
                } else emit(logInResult)
            }
        }
    }
}