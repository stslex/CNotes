package com.stslex.feature_profile.domain.abstraction

fun interface SignOutInteractor {
    suspend operator fun invoke()
}