package com.example.feature_auth_code.ui.use_cases

import android.app.Activity
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AuthCodeUtilImpl : AuthCodeUtil {

    override suspend fun signIn(
        verificationId: String,
        code: String,
        activity: Activity
    ): ValueState<Unit> = suspendCancellableCoroutine { continuation ->
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        Firebase.auth.signInWithCredential(credential).addOnCompleteListener(activity) {
            if (it.isSuccessful) {
                continuation.resume(ValueState.Success(Unit))
            } else {
                continuation.resume(ValueState.Failure(it.exception!!))
            }
        }
    }
}