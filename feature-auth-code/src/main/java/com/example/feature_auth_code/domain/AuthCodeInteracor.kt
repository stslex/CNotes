package com.example.feature_auth_code.domain

import android.app.Activity
import com.example.feature_auth_code.ui.use_cases.AuthCodeUseCase
import com.stslex.core.ValueState
import com.stslex.core_firebase_auth.data.FirebaseAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AuthCodeInteracor {

    suspend fun logIn(
        verificationId: String,
        code: String,
        activity: Activity
    ): Flow<ValueState<Unit>>

    class Base(
        private val authCodeUseCase: AuthCodeUseCase,
        private val authFirebase: FirebaseAuthRepository
    ) : AuthCodeInteracor {

        override suspend fun logIn(
            verificationId: String,
            code: String,
            activity: Activity
        ): Flow<ValueState<Unit>> = flow {
            val result = authCodeUseCase.signIn(verificationId, code, activity)
            if (result is ValueState.Success) {
                emit(authFirebase.saveUser())
            } else emit(result)
            emit(result)
        }
    }
}