package com.stslex.cnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import androidx.navigation.compose.rememberNavController
import com.stslex.cnotes.ui.navigation.BottomNavScreen
import com.stslex.cnotes.ui.navigation.NavigationHost
import com.stslex.cnotes.ui.theme.CNotesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CNotesTheme {
                val navHostController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = bottomAppBar(navController = navHostController)
                ) {
                    NavigationHost(navController = navHostController)
                }
            }
        }
    }

    private fun bottomAppBar(navController: NavController): @Composable () -> Unit = {
        BottomNavigation {
            val selectedItem = remember { mutableStateOf(bottomScreens.first().route) }
            bottomScreens.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(screen.icon, screen.route) },
                    label = { Text(text = screen.route) },
                    selected = selectedItem.value == screen.route,
                    onClick = {
                        selectedItem.value = screen.route
                        navController.navigate(
                            route = screen.route,
                            builder = navController.navOptionBuilder
                        )
                    },
                    alwaysShowLabel = false
                )
            }
        }
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

    companion object {
        private val bottomScreens = listOf(BottomNavScreen.NotesScreen, BottomNavScreen.ToDoScreen)
    }
}