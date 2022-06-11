package com.example.feature_auth_phonenumber.ui.use_cases

import com.example.feature_auth_phonenumber.core.SignValueState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

@JvmInline
value class SignCompleteListener(
    private val sendResult: (SignValueState) -> Unit
) : OnCompleteListener<AuthResult> {

    override fun onComplete(task: Task<AuthResult>) = if (task.isSuccessful) {
        sendResult(SignValueState.Success)
    } else {
        sendResult(SignValueState.Failure(task.exception!!))
    }
}