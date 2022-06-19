package com.stslex.feature_profile.ui.usecases.abstraction

interface ProfileUseCase {
    fun initFirebaseApp()
    suspend fun signOut()
}