package com.stslex.cnotes.ui

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.feature_note_list.navigation.NOTE_LIST_TOP_LEVEL_DESTINATION
import com.stslex.cnotes.navigation.AppTopLevelNavigation
import com.stslex.cnotes.navigation.NavigationHost
import com.stslex.core_navigation.TopLevelDestination
import com.stslex.core_ui.components.ClearRippleTheme
import com.stslex.core_ui.theme.AppTheme
import com.stslex.feature_single_note.navigation.SingleNoteDestination
import com.stslex.feature_todo.navigation.TODO_TOP_LEVEL_DESTINATION

private val topLevelDestinationList =
    listOf(NOTE_LIST_TOP_LEVEL_DESTINATION, TODO_TOP_LEVEL_DESTINATION)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppCreator(windowSizeClass: WindowSizeClass) {
    AppTheme(dynamicColor = Build.VERSION.SDK_INT > 30) {
        val navController = rememberNavController()
        val niaTopLevelNavigation = remember(navController) {
            AppTopLevelNavigation(navController)
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (
                    windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact &&
                    currentDestination?.route?.contains(SingleNoteDestination.route) == false
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
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                if (
                    windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact &&
                    currentDestination?.route?.contains(SingleNoteDestination.route) == false
                ) {
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

@Composable
private fun AppNavRail(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        topLevelDestinationList.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationRailItem(
                selected = selected,
                onClick = { onNavigateToTopLevelDestination(destination) },
                icon = {
                    Icon(
                        if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(destination.iconTextId))
                },
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
private fun AppBottomBar(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
            NavigationBar(
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                    )
                ),
                tonalElevation = 0.dp
            ) {

                topLevelDestinationList.forEach { destination ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == destination.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = { onNavigateToTopLevelDestination(destination) },
                        icon = {
                            Icon(
                                if (selected) {
                                    destination.selectedIcon
                                } else {
                                    destination.unselectedIcon
                                },
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(destination.iconTextId)) },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    }
}