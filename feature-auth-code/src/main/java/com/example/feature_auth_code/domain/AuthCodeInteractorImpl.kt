package com.example.feature_auth_code.domain

import android.app.Activity
import com.example.feature_auth_code.ui.use_cases.AuthCodeUtil
import com.stslex.core.ValueState
import com.stslex.core_firebase_auth.data.FirebaseAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthCodeInteractorImpl(
    private val authCodeUseCase: AuthCodeUtil, private val authFirebase: FirebaseAuthRepository
) : AuthCodeInteractor {

    override suspend fun logIn(
        verificationId: String, code: String, activity: Activity
    ): Flow<ValueState<Unit>> = flow {
        val result = authCodeUseCase.signIn(verificationId, code, activity)
        if (result is ValueState.Success) {
            emit(authFirebase.saveUser())
        } else emit(result)
        emit(result)
    }
}