package com.stslex.feature_profile.domain.realisation

import com.stslex.feature_profile.data.ProfileRepository
import com.stslex.feature_profile.domain.abstraction.SignOutInteractor

class SignOutInteractorImpl(
    private val repository: ProfileRepository
) : SignOutInteractor {

    override suspend fun invoke() {
        repository.signOut()
    }
}