package com.example.feature_auth_phonenumber.ui.use_cases

import android.app.Activity
import com.example.feature_auth_phonenumber.core.SignValueState

interface AuthPhoneNumberUtil {
    suspend fun signIn(activity: Activity, phoneNumber: String): SignValueState
}