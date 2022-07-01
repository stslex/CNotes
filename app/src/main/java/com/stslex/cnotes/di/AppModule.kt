package com.stslex.cnotes.di

import com.stslex.cnotes.utils.ShortcutBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single<ShortcutBuilder> {
        ShortcutBuilder.Base(androidApplication())
    }
}