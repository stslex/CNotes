package com.stslex.feature_profile.ui.usecases.implementation

import com.stslex.feature_profile.data.abstraction.ProfileRepository
import com.stslex.feature_profile.ui.usecases.abstraction.ProfileUseCase

class ProfileUseCaseImpl(
    private val repository: ProfileRepository
) : ProfileUseCase {

    override fun initFirebaseApp() {
        repository.initFirebaseApp()
    }

    override suspend fun signOut() {
        repository.signOut()
    }
}