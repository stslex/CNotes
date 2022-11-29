package com.stslex.cnotes.shortcut

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import st.slex.feature_main.ui.MainActivity

data class Shortcut(
    val labelSource: Int,
    val shortLabelSource: Int,
    val longLabelSource: Int,
    val disabledMessageSource: Int,
    val iconSource: Int,
    val intentDestination: String
) : ShortcutAction {

    override fun push(context: Context) {
        ShortcutManagerCompat.pushDynamicShortcut(
            context,
            shortcutInfoCompat(context)
        )
    }

    private val shortcutInfoCompat: (context: Context) -> ShortcutInfoCompat
        get() = { context ->
            ShortcutInfoCompat.Builder(
                context, context.getString(labelSource)
            ).setShortLabel(context.getString(shortLabelSource))
                .setLongLabel(context.getString(longLabelSource))
                .setDisabledMessage(context.getString(disabledMessageSource))
                .setIcon(IconCompat.createWithResource(context, iconSource))
                .setIntent(context.shortcutIntent).build()
        }

    private val Context.shortcutIntent: Intent
        get() = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("$APP_URI_PREFIX${intentDestination}"),
            this,
            MainActivity::class.java
        )

    companion object {
        private const val APP_URI_PREFIX = "app://"
    }
}