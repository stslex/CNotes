package com.stslex.cnotes.ui.activity

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.stslex.cnotes.ui.navigation.BottomNavScreen

private val bottomScreens = listOf(BottomNavScreen.NotesScreen, BottomNavScreen.ToDoScreen)

fun bottomAppBar(navController: NavController): @Composable () -> Unit = {
    NavigationBar {
        val selectedItem = remember { mutableStateOf(bottomScreens.first().route) }
        bottomScreens.forEach { screen ->
            NavigationBarItem(
                icon = bottomAppBarIcon(screen = screen),
                label = bottomAppBarText(screen = screen),
                selected = selectedItem.value == screen.route,
                onClick = onBottomAppBarClick(navController, screen, selectedItem),
                alwaysShowLabel = false
            )
        }
    }
}

private fun bottomAppBarIcon(screen: BottomNavScreen): @Composable () -> Unit = {
    Icon(
        imageVector = screen.icon,
        contentDescription = screen.route
    )
}

private fun bottomAppBarText(screen: BottomNavScreen): @Composable () -> Unit = {
    Text(
        text = screen.route,
        style = MaterialTheme.typography.titleMedium
    )
}


private fun onBottomAppBarClick(
    navController: NavController,
    screen: BottomNavScreen,
    selectedItem: MutableState<String>
): () -> Unit = {
    selectedItem.value = screen.route
    navController.navigate(
        route = screen.route,
        builder = navController.navOptionBuilder
    )
}

private val NavController.navOptionBuilder: NavOptionsBuilder.() -> Unit
    get() = {
        popUpTo(
            route = graph.startDestinationRoute ?: BottomNavScreen.NotesScreen.route,
            popUpToBuilder = {
                inclusive = true
                saveState = true
            }
        )
        launchSingleTop = true
        restoreState = true
    }