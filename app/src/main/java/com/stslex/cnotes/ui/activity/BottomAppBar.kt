package com.stslex.cnotes.ui.activity

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import com.stslex.cnotes.ui.navigation.BottomNavScreen

private val bottomScreens = listOf(BottomNavScreen.NotesScreen, BottomNavScreen.ToDoScreen)

fun bottomAppBar(navController: NavController): @Composable () -> Unit = {
    NavigationBar {
        val selectedItem = remember { mutableStateOf(bottomScreens.first().route) }
        bottomScreens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, screen.route) },
                label = { Text(text = screen.route) },
                selected = selectedItem.value == screen.route,
                onClick = onBottomAppBarClick(navController, screen, selectedItem),
                alwaysShowLabel = false
            )
        }
    }
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
            popUpToBuilder = popUpToBuilder
        )
        launchSingleTop = true
        restoreState = true
    }

private val popUpToBuilder: PopUpToBuilder.() -> Unit
    get() = {
        inclusive = true
        saveState = true
    }