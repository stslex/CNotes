package st.slex.feature_main.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stslex.core_ui.theme.AppTheme
import st.slex.feature_main.navigation.AppTopLevelNavigation
import st.slex.feature_main.navigation.NavigationHost
import st.slex.feature_main.utils.MainScreenExt.isCompact
import st.slex.feature_main.utils.MainScreenExt.isTopLevel
import st.slex.feature_main.utils.MainScreenExt.mainContentInsets

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppCreator(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController
) {
    AppTheme {
        val niaTopLevelNavigation = remember(navController) {
            AppTopLevelNavigation(navController)
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = bottomAppBar(
                windowSizeClass = windowSizeClass,
                currentDestination = currentDestination,
                niaTopLevelNavigation = niaTopLevelNavigation
            ),
            content = scaffoldAppContent(
                windowSizeClass,
                currentDestination,
                niaTopLevelNavigation
            ) { paddingValues ->
                NavigationHost(
                    navController = navController,
                    modifier = Modifier
                        .padding(paddingValues)
                        .consumedWindowInsets(paddingValues)
                )
            }
        )
    }
}

private fun scaffoldAppContent(
    windowSizeClass: WindowSizeClass,
    currentDestination: NavDestination?,
    niaTopLevelNavigation: AppTopLevelNavigation,
    navigationHost: @Composable (PaddingValues) -> Unit
): @Composable (
    paddingValues: PaddingValues
) -> Unit = { paddingValues ->
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
        navigationHost(paddingValues)
    }
}

private fun bottomAppBar(
    windowSizeClass: WindowSizeClass,
    currentDestination: NavDestination?,
    niaTopLevelNavigation: AppTopLevelNavigation
): @Composable () -> Unit = {
    AnimatedVisibility(
        visible = windowSizeClass.isCompact && currentDestination.isTopLevel
    ) {
        AppBottomBar(
            onNavigateToTopLevelDestination = niaTopLevelNavigation::navigateTo,
            currentDestination = currentDestination
        )
    }
}