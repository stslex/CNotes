package com.stslex.feature_auth_phonenumber.ui.use_cases

import android.app.Activity
import com.stslex.feature_auth_phonenumber.core.SignValueState

interface AuthPhoneNumberUtil {
    suspend fun signIn(activity: Activity, phoneNumber: String): SignValueState
}