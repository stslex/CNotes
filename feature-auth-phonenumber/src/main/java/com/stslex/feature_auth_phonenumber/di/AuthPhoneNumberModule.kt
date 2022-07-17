package com.stslex.feature_auth_phonenumber.di

import com.stslex.feature_auth_phonenumber.domain.AuthPhoneNumberInteractor
import com.stslex.feature_auth_phonenumber.domain.AuthPhoneNumberInteractorImpl
import com.stslex.feature_auth_phonenumber.ui.AuthPhoneViewModel
import com.stslex.feature_auth_phonenumber.ui.use_cases.AuthPhoneNumberUtil
import com.stslex.feature_auth_phonenumber.ui.use_cases.AuthPhoneNumberUtilImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

@Module
class AuthPhoneNumberModule {
    val module = module {
        viewModelOf(::AuthPhoneViewModel)
        factoryOf(::AuthPhoneNumberInteractorImpl) { bind<AuthPhoneNumberInteractor>() }
        factoryOf(::AuthPhoneNumberUtilImpl) { bind<AuthPhoneNumberUtil>() }
    }
}