package com.stslex.cnotes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.core.view.WindowCompat
import com.stslex.cnotes.ui.AppCreator
import com.stslex.core_firebase.abstraction.FirebaseAppInitialisationUtil
import com.stslex.core_resources.R.string
import com.stslex.feature_profile.navigation.ProfileDestination
import com.stslex.feature_single_note.navigation.SingleNoteDestination
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val firebaseAppInitialisationUtil: FirebaseAppInitialisationUtil by inject()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAppInitialisationUtil.invoke()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent { AppCreator(calculateWindowSizeClass(this)) }
        ShortcutManagerCompat.pushDynamicShortcut(this, shortcutCreateNewNote)
        ShortcutManagerCompat.pushDynamicShortcut(this, shortcutProfile)
    }

    private val shortcutCreateNewNote: ShortcutInfoCompat by lazy {
        ShortcutInfoCompat.Builder(this, getString(string.lb_short_shortcut_create))
            .setShortLabel(getString(string.lb_short_shortcut_create))
            .setLongLabel(getString(string.lb_long_shortcut_create))
            .setDisabledMessage(getString(string.lb_shortcut_disabled_message))
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_baseline_add_24))
            .setIntent(shortcutIntent.invoke("${SingleNoteDestination.route}/-1"))
            .build()
    }

    private val shortcutProfile: ShortcutInfoCompat by lazy {
        ShortcutInfoCompat.Builder(this, getString(string.lb_short_shortcut_profile))
            .setShortLabel(getString(string.lb_short_shortcut_profile))
            .setLongLabel(getString(string.lb_long_shortcut_profile))
            .setDisabledMessage(getString(string.lb_shortcut_disabled_message))
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_baseline_person_outline_24))
            .setIntent(shortcutIntent.invoke(ProfileDestination.route))
            .build()
    }

    private val shortcutIntent: (String) -> Intent
        get(): (String) -> Intent = { url ->
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("app://$url"),
                this,
                MainActivity::class.java
            )
        }
}