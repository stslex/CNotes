package com.example.feature_auth_code.data

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface AuthCodeRepository {

    suspend fun saveUser(): ValueState<Unit>

    class Base : AuthCodeRepository {

        private val firebaseUser: FirebaseUser by lazy {
            Firebase.auth.currentUser!!
        }

        override suspend fun saveUser(): ValueState<Unit> = suspendCoroutine { continuation ->
            Firebase.database.reference.child("users").child(firebaseUser.uid).updateChildren(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        continuation.resume(ValueState.Success(Unit))
                    } else {
                        continuation.resume(ValueState.Failure(it.exception!!))
                    }
                }
        }

        private val user: Map<String, Any> by lazy {
            mapOf<String, Any>(
                "id" to firebaseUser.uid,
                "phone" to firebaseUser.phoneNumber!!
            )
        }
    }
}