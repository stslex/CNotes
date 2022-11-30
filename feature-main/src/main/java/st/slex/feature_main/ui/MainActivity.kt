package st.slex.feature_main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stslex.core_firebase.utils.abstraction.FirebaseAppInitialisationUtil
import org.koin.android.ext.android.inject
import st.slex.feature_main.di.ActivityComponent
import st.slex.feature_main.di.ActivityDependencies
import st.slex.feature_main.ui.components.AppCreator

class MainActivity : ComponentActivity() {

    private val shortcutBuilder: ShortcutBuilder by inject()
    private val activityComponent: ActivityComponent by inject()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        shortcutBuilder()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SetUpUIEffects()
            val navController = rememberAnimatedNavController()
            setUpDependencies(navController)
            AppCreator(
                windowSizeClass = calculateWindowSizeClass(this),
                navController = navController
            )
        }
    }

    private fun setUpDependencies(navController: NavHostController) {
        val dependencies = ActivityDependencies(navController)
        activityComponent(dependencies)
    }

    @Composable
    private fun SetUpUIEffects() {
        val systemUiController: SystemUiController = rememberSystemUiController()
        val iconsDark = !isSystemInDarkTheme()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = iconsDark
            )
        }
    }
}