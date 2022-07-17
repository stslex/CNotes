package com.stslex.feature_auth_code.ui.use_cases

import android.app.Activity
import com.stslex.core.ValueState

interface AuthCodeUtil {

    suspend fun signIn(
        verificationId: String,
        code: String,
        activity: Activity
    ): ValueState<Unit>
}