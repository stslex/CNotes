package com.stslex.core_firebase_auth.data

import com.stslex.core.ValueState

interface FirebaseAuthRepository {
    suspend fun saveUser(): ValueState<Unit>
}