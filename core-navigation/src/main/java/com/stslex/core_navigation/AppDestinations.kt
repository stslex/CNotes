package com.stslex.core_navigation

sealed interface AppDestinations {
    val route: String
    val destination: String
}