package com.stslex.cnotes.di

import com.stslex.cnotes.utils.ShortcutBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

@Single
class AppComponent : KoinComponent {
    val appModules = module {
        single<ShortcutBuilder> {
            ShortcutBuilder.Base(androidApplication())
        }
    }
}