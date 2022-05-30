package com.stslex.feature_profile.di

import com.stslex.feature_profile.data.ProfileRepository
import com.stslex.feature_profile.ui.ProfileViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val profileModule = module {
    singleOf(::ProfileViewModel)
    singleOf(ProfileRepository::Base) { bind<ProfileRepository>() }
}