package com.example.feature_auth_phonenumber.domain

import android.app.Activity
import com.example.feature_auth_phonenumber.core.SignValueState
import kotlinx.coroutines.flow.Flow

interface AuthPhoneNumberInteractor {
    suspend fun login(activity: Activity, phoneNumber: String): Flow<SignValueState>
}