package com.stslex.cnotes.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.stslex.cnotes.MainActivity
import com.stslex.cnotes.R.drawable
import com.stslex.core_navigation.destinations.ProfileDestination
import com.stslex.core_navigation.destinations.SingleNoteDestination
import com.stslex.core_ui.R

fun interface ShortcutBuilder {

    operator fun invoke()

    class Base(private val context: Context) : ShortcutBuilder {

        override operator fun invoke() {
            listOfShortcuts.forEach {
                ShortcutManagerCompat.pushDynamicShortcut(context, it)
            }
        }

        private val listOfShortcuts by lazy {
            listOf(shortcutCreateNewNote, shortcutProfile)
        }

        private val shortcutCreateNewNote: ShortcutInfoCompat by lazy {
            ShortcutInfoCompat.Builder(
                context, context.getString(R.string.lb_short_shortcut_create)
            ).setShortLabel(context.getString(R.string.lb_short_shortcut_create))
                .setLongLabel(context.getString(R.string.lb_long_shortcut_create))
                .setDisabledMessage(context.getString(R.string.lb_shortcut_disabled_message))
                .setIcon(
                    IconCompat.createWithResource(
                        context, drawable.ic_baseline_add_24
                    )
                ).setIntent(shortcutIntent("${SingleNoteDestination.route}/-1")).build()
        }

        private val shortcutProfile: ShortcutInfoCompat by lazy {
            ShortcutInfoCompat.Builder(
                context, context.getString(R.string.lb_short_shortcut_profile)
            ).setShortLabel(context.getString(R.string.lb_short_shortcut_profile))
                .setLongLabel(context.getString(R.string.lb_long_shortcut_profile))
                .setDisabledMessage(context.getString(R.string.lb_shortcut_disabled_message))
                .setIcon(
                    IconCompat.createWithResource(
                        context, drawable.ic_baseline_person_outline_24
                    )
                ).setIntent(shortcutIntent(ProfileDestination.route)).build()
        }

        private val shortcutIntent: (String) -> Intent
            get(): (String) -> Intent = { url ->
                Intent(
                    Intent.ACTION_VIEW, Uri.parse("app://$url"), context, MainActivity::class.java
                )
            }
    }
}