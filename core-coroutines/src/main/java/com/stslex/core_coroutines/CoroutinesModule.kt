package com.stslex.core_coroutines

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coroutinesModule = module {
    single<AppDispatchers> { AppDispatchers.Base() }
    singleOf(CoroutinesHandler::Base) { bind<CoroutinesHandler>() }
}