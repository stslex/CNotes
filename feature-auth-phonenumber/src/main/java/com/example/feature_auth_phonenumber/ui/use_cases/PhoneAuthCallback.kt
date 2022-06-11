package com.example.feature_auth_phonenumber.ui.use_cases

import com.example.feature_auth_phonenumber.core.SignValueState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PhoneAuthCallback(
    private val sendResult: (SignValueState) -> Unit
) : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    private val completeListener: OnCompleteListener<AuthResult> by lazy {
        SignCompleteListener(sendResult)
    }

    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        Firebase.auth
            .signInWithCredential(credential)
            .addOnCompleteListener(completeListener)
    }

    override fun onVerificationFailed(exception: FirebaseException) =
        sendResult(SignValueState.Failure(exception))

    override fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        super.onCodeSent(verificationId, token)
        sendResult(SignValueState.SendCode(verificationId, token))
    }
}