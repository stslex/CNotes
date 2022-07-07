package com.stslex.core_remote_data_source.di

import com.stslex.core_remote_data_source.service.abstraction.FirebaseNotesService
import com.stslex.core_remote_data_source.service.implementation.FirebaseNotesServiceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class RemoteDataSourceModule {
    val module = module {
        singleOf(::FirebaseNotesServiceImpl) { bind<FirebaseNotesService>() }
    }
}