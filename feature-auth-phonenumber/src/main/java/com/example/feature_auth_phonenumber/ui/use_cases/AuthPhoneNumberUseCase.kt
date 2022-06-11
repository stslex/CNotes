package com.example.feature_auth_phonenumber.ui.use_cases

import android.app.Activity
import com.example.feature_auth_phonenumber.core.SignValueState
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface AuthPhoneNumberUseCase {

    suspend fun signIn(activity: Activity, phoneNumber: String): SignValueState

    class Base : AuthPhoneNumberUseCase {

        override suspend fun signIn(
            activity: Activity,
            phoneNumber: String
        ): SignValueState = suspendCoroutine { continuation ->
            val callback = PhoneAuthCallback(continuation::resume)
            val options = PhoneAuthOptions.newBuilder(Firebase.auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(callback)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }
}