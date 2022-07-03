package com.example.feature_auth_phonenumber.domain

import android.app.Activity
import com.example.feature_auth_phonenumber.core.SignValueState
import com.example.feature_auth_phonenumber.ui.use_cases.AuthPhoneNumberUtil
import com.stslex.core.ValueState
import com.stslex.core_firebase_auth.data.FirebaseAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthPhoneNumberInteractorImpl(
    private val authPhoneNumberUseCase: AuthPhoneNumberUtil,
    private val authPhoneNumberRepository: FirebaseAuthRepository
) : AuthPhoneNumberInteractor {

    override suspend fun login(
        activity: Activity,
        phoneNumber: String
    ): Flow<SignValueState> = flow {
        val signInResult = authPhoneNumberUseCase.signIn(activity, phoneNumber)
        val result = if (signInResult is SignValueState.Success) {
            when (val saveResult = authPhoneNumberRepository.saveUser()) {
                is ValueState.Success -> SignValueState.Success
                is ValueState.Failure -> SignValueState.Failure(saveResult.exception)
                is ValueState.Loading -> SignValueState.Loading
            }
        } else signInResult
        emit(result)
    }
}