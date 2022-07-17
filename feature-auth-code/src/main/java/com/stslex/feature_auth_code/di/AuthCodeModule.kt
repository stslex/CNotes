package com.stslex.feature_auth_code.di

import com.stslex.feature_auth_code.domain.AuthCodeInteractor
import com.stslex.feature_auth_code.domain.AuthCodeInteractorImpl
import com.stslex.feature_auth_code.ui.AuthCodeViewModel
import com.stslex.feature_auth_code.ui.use_cases.AuthCodeUtil
import com.stslex.feature_auth_code.ui.use_cases.AuthCodeUtilImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

@Module
class AuthCodeModule {
    val module = module {
        viewModelOf(::AuthCodeViewModel)
        factoryOf(::AuthCodeUtilImpl) { bind<AuthCodeUtil>() }
        factoryOf(::AuthCodeInteractorImpl) { bind<AuthCodeInteractor>() }
    }
}