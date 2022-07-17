package com.stslex.cnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stslex.cnotes.ui.AppCreator
import com.stslex.cnotes.utils.ShortcutBuilder
import com.stslex.core_firebase.utils.abstraction.FirebaseAppInitialisationUtil
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val firebaseAppInitialisationUtil: FirebaseAppInitialisationUtil by inject()
    private val shortcutBuilder: ShortcutBuilder by inject()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAppInitialisationUtil.invoke()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()
            val iconsDark = !isSystemInDarkTheme()
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = iconsDark
                )
            }
            AppCreator(calculateWindowSizeClass(this))
        }
        shortcutBuilder.invoke()
    }
}