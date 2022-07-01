package com.stslex.cnotes.ui

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.feature_note_list.navigation.noteListTopLevelDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.stslex.cnotes.navigation.AppTopLevelNavigation
import com.stslex.cnotes.navigation.NavigationHost
import com.stslex.core_navigation.destinations.NoteListDestination
import com.stslex.core_navigation.destinations.TodoDestination
import com.stslex.core_ui.theme.AppTheme
import com.stslex.feature_todo.navigation.todoTopLevelDestination

val topLevelDestinationList = listOf(noteListTopLevelDestination, todoTopLevelDestination)
private val listOfDestinations = listOf(
    NoteListDestination.destination,
    TodoDestination.route
)

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun AppCreator(windowSizeClass: WindowSizeClass) {
    AppTheme(dynamicColor = Build.VERSION.SDK_INT > 30) {
        val navController = rememberAnimatedNavController()
        val niaTopLevelNavigation = remember(navController) {
            AppTopLevelNavigation(navController)
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                AnimatedVisibility(
                    visible = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact && listOfDestinations.contains(
                        currentDestination?.route
                    )
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
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
            ) {
                AnimatedVisibility(visible = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact) {
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