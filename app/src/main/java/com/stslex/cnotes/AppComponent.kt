package com.stslex.cnotes

import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.feature_main.di.ActivityComponent
import st.slex.feature_main.di.ActivityComponentImpl
import st.slex.feature_main.ui.ShortcutBuilder
import com.stslex.cnotes.shortcut.ShortcutBuilderImpl

@Single
class AppComponent : KoinComponent {
    val appModules = module {
        single<ShortcutBuilder> {
            ShortcutBuilderImpl(androidContext())
        }
        singleOf(::ActivityComponentImpl) {
            bind<ActivityComponent>()
        }
    }
}