package com.example.feature_auth_code.di

import com.example.feature_auth_code.data.AuthCodeRepository
import com.example.feature_auth_code.domain.AuthCodeInteracor
import com.example.feature_auth_code.ui.AuthCodeViewModel
import com.example.feature_auth_code.ui.use_cases.AuthCodeUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authCodeModule = module {
    viewModelOf(::AuthCodeViewModel)
    singleOf(AuthCodeRepository::Base) { bind<AuthCodeRepository>() }
    singleOf(AuthCodeUseCase::Base) { bind<AuthCodeUseCase>() }
    singleOf(AuthCodeInteracor::Base) { bind<AuthCodeInteracor>() }
}