package com.stslex.feature_profile.domain.realisation

import com.stslex.core.ValueState
import com.stslex.feature_profile.data.ProfileRepository
import com.stslex.feature_profile.domain.abstraction.CheckUserAuthInteractor

class CheckUserAuthInteractorImpl(
    private val repository: ProfileRepository
) : CheckUserAuthInteractor {

    override fun invoke(): ValueState<Boolean> = repository.isUserAuth()
}