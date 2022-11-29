package com.stslex.cnotes.shortcut

import android.content.Context
import st.slex.feature_main.ui.ShortcutBuilder
import com.stslex.core.error.AppLogger

class ShortcutBuilderImpl(
    private val context: Context
) : ShortcutBuilder {

    private val listOfShortcuts: List<ShortcutAction> by lazy {
        listOf(AppShortcuts.Profile, AppShortcuts.CreateNewNote)
            .map { it.shortcut }
    }

    override fun invoke() {
        listOfShortcuts.forEach { shortcut ->
            try {
                shortcut.push(context)
            } catch (exception: Exception) {
                AppLogger.logException(exception, javaClass.simpleName)
            }
        }
    }
}