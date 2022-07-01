package com.stslex.core_navigation.destinations

sealed interface AppDestinations {
    val route: String
    val destination: String
}