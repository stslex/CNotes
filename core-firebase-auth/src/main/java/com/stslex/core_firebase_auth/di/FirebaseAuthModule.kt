package com.stslex.core_firebase_auth.di

import com.stslex.core_firebase_auth.data.FirebaseAuthRepository
import com.stslex.core_firebase_auth.data.FirebaseAuthRepositoryImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@Module
class FirebaseAuthModule {

    val module = module {
        singleOf(::FirebaseAuthRepositoryImpl) { bind<FirebaseAuthRepository>() }
    }
}
