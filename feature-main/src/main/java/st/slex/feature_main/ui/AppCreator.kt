package st.slex.feature_main.ui

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stslex.core_ui.theme.AppTheme
import st.slex.feature_main.navigation.AppTopLevelNavigation
import st.slex.feature_main.navigation.NavigationHost
import st.slex.feature_main.utils.MainScreenExt.isCompact
import st.slex.feature_main.utils.MainScreenExt.isTopLevel
import st.slex.feature_main.utils.MainScreenExt.mainContentInsets

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@Composable
fun AppCreator(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController
) {
    AppTheme(dynamicColor = Build.VERSION.SDK_INT > 30) {
        val niaTopLevelNavigation = remember(navController) {
            AppTopLevelNavigation(navController)
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                AnimatedVisibility(
                    visible = windowSizeClass.isCompact && currentDestination.isTopLevel
                ) {
                    AppBottomBar(
                        onNavigateToTopLevelDestination = niaTopLevelNavigation::navigateTo,
                        currentDestination = currentDestination
                    )
                }
            }
        ) { paddingValues ->
            Row(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(mainContentInsets())
            ) {
                AnimatedVisibility(visible = !windowSizeClass.isCompact) {
                    AppNavRail(
                        onNavigateToTopLevelDestination = niaTopLevelNavigation::navigateTo,
                        currentDestination = currentDestination,
                        modifier = Modifier.safeDrawingPadding()
                    )
                }
                NavigationHost(
                    navController = navController,
                    modifier = Modifier
                        .padding(paddingValues)
                        .consumedWindowInsets(paddingValues)
                )
            }
        }
    }
}