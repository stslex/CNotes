package com.stslex.cnotes

import st.slex.feature_main.di.ActivityComponent
import st.slex.feature_main.di.ActivityComponentImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.feature_main.utils.ShortcutBuilder

@Single
class AppComponent : KoinComponent {
    val appModules = module {
        single<ShortcutBuilder> {
            ShortcutBuilder.Base(androidApplication())
        }
        singleOf(::ActivityComponentImpl) {
            bind<ActivityComponent>()
        }
    }
}