package com.stslex.core

import org.koin.core.annotation.Module
import org.koin.dsl.module


@Module
class CoroutinesModule {
    val module = module {
        single<AppDispatchers> { AppDispatchersImpl() }
    }
}