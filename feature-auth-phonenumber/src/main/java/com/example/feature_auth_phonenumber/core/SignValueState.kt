package com.example.feature_auth_phonenumber.core

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider

sealed class SignValueState {

    data class SendCode(
        val id: String,
        val token: PhoneAuthProvider.ForceResendingToken
    ) : SignValueState()

    data class Success(val user: FirebaseUser) : SignValueState()
    data class Failure(val exception: Exception) : SignValueState()
}