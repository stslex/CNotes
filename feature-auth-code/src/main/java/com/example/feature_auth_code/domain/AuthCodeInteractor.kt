package com.example.feature_auth_code.domain

import android.app.Activity
import com.stslex.core.ValueState
import kotlinx.coroutines.flow.Flow

interface AuthCodeInteractor {
    suspend fun logIn(
        verificationId: String,
        code: String,
        activity: Activity
    ): Flow<ValueState<Unit>>
}