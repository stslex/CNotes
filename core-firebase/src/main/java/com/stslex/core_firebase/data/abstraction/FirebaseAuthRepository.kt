package com.stslex.core_firebase.data.abstraction

import com.stslex.core.ValueState

interface FirebaseAuthRepository {
    suspend fun saveUser(): ValueState<Unit>
}