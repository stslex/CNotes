package com.example.feature_auth_phonenumber.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface AuthPhoneNumberRepository {

    suspend fun saveUser(): Flow<ValueState<Unit>>

    class Base : AuthPhoneNumberRepository {

        private val firebaseUser by lazy {
            Firebase.auth.currentUser!!
        }

        override suspend fun saveUser(): Flow<ValueState<Unit>> = callbackFlow {
            val updateUserTask: Task<DocumentReference> =
                Firebase.firestore.collection("users").add(user)
            updateUserTask.addOnSuccessListener {
                trySend(ValueState.Success(Unit))
            }.addOnCompleteListener {
                if (it.isSuccessful) {
                    trySendBlocking(ValueState.Success(Unit))
                } else {
                    trySendBlocking(ValueState.Failure(it.exception!!))
                }
            }
            awaitClose { Firebase.firestore.clearPersistence() }
        }

        private val user: Map<String, Any> by lazy {
            mapOf<String, Any>(
                "id" to firebaseUser.uid,
                "phone" to firebaseUser.phoneNumber!!
            )
        }
    }
}