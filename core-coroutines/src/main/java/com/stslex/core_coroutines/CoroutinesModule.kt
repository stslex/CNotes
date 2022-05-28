package com.stslex.core_coroutines

import org.koin.dsl.module

val coroutinesModule = module {
    single<AppDispatchers> { AppDispatchers.Base() }
}