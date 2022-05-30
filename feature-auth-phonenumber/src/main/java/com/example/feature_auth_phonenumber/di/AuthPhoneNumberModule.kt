package com.example.feature_auth_phonenumber.di

import com.example.feature_auth_phonenumber.data.AuthPhoneNumberRepository
import com.example.feature_auth_phonenumber.domain.AuthPhoneNumberInteractor
import com.example.feature_auth_phonenumber.ui.AuthPhoneViewModel
import com.example.feature_auth_phonenumber.ui.use_cases.AuthPhoneNumberUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authPhoneNumberModule = module {
    viewModelOf(::AuthPhoneViewModel)
    singleOf(AuthPhoneNumberRepository::Base) { bind<AuthPhoneNumberRepository>() }
    singleOf(AuthPhoneNumberInteractor::Base) { bind<AuthPhoneNumberInteractor>() }
    singleOf(AuthPhoneNumberUseCase::Base) { bind<AuthPhoneNumberUseCase>() }
}