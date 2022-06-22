package com.stslex.feature_profile.di

import com.stslex.feature_profile.data.abstraction.ProfileRepository
import com.stslex.feature_profile.data.implementation.ProfileRepositoryImpl
import com.stslex.feature_profile.ui.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val profileModule = module {
    viewModelOf(::ProfileViewModel)
    singleOf(::ProfileRepositoryImpl) { bind<ProfileRepository>() }
}