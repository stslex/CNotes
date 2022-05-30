package com.example.feature_auth_phonenumber.ui.use_cases

import android.app.Activity
import com.example.feature_auth_phonenumber.core.SignValueState
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit

interface AuthPhoneNumberUseCase {

    suspend fun signIn(activity: Activity, phoneNumber: String): Flow<SignValueState>

    class Base : AuthPhoneNumberUseCase {

        override suspend fun signIn(
            activity: Activity,
            phoneNumber: String
        ): Flow<SignValueState> = callbackFlow {
            val options = PhoneAuthOptions.newBuilder(Firebase.auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        Firebase.auth.signInWithCredential(credential)
                            .addOnCompleteListener(activity) {
                                if (it.isSuccessful) {
                                    trySendBlocking(SignValueState.Success(it.result.user!!))
                                } else {
                                    trySendBlocking(SignValueState.Failure(it.exception!!))
                                }
                            }
                    }

                    override fun onVerificationFailed(exception: FirebaseException) {
                        trySendBlocking(SignValueState.Failure(exception))
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        token: PhoneAuthProvider.ForceResendingToken
                    ) {
                        super.onCodeSent(verificationId, token)
                        trySendBlocking(SignValueState.SendCode(verificationId, token))
                    }
                })
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            awaitClose { }
        }
    }
}