package com.stslex.core_firebase_auth.di

import com.stslex.core_firebase_auth.data.FirebaseAuthRepository
import com.stslex.core_firebase_auth.data.FirebaseAuthRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val firebaseAuthModule = module {
    singleOf(::FirebaseAuthRepositoryImpl) { bind<FirebaseAuthRepository>() }
}