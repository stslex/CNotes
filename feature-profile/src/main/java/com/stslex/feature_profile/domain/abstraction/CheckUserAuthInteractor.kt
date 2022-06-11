package com.stslex.feature_profile.domain.abstraction

import com.stslex.core.ValueState

interface CheckUserAuthInteractor {
    fun invoke(): ValueState<Boolean>
}