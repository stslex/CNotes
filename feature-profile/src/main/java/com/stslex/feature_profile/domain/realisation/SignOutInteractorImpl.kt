package com.stslex.feature_profile.domain.realisation

import com.stslex.feature_profile.data.ProfileRepositoryOld
import com.stslex.feature_profile.domain.abstraction.SignOutInteractor

class SignOutInteractorImpl(
    private val repository: ProfileRepositoryOld
) : SignOutInteractor {

    override suspend fun invoke() {
        repository.signOut()
    }
}