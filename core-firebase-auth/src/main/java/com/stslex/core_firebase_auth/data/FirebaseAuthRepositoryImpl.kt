package com.stslex.core_firebase_auth.data

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.stslex.core.ValueState
import com.stslex.core_firebase.FirebaseReferences.CHILD_ID
import com.stslex.core_firebase.FirebaseReferences.CHILD_PHONE
import com.stslex.core_firebase.FirebaseReferences.NODE_USERS
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthRepositoryImpl : FirebaseAuthRepository {

    private val firebaseUser: FirebaseUser by lazy {
        Firebase.auth.currentUser!!
    }

    private val firebaseReference: DatabaseReference by lazy {
        Firebase.database.reference.child(NODE_USERS).child(firebaseUser.uid)
    }

    override suspend fun saveUser(): ValueState<Unit> = suspendCoroutine { continuation ->
        firebaseReference.updateChildren(currentUserMap)
            .addOnCompleteListener(SaveUserCompleteListener(continuation::resume))
    }

    private val currentUserMap: Map<String, Any> by lazy {
        mapOf<String, Any>(
            CHILD_ID to firebaseUser.uid,
            CHILD_PHONE to firebaseUser.phoneNumber!!
        )
    }
}