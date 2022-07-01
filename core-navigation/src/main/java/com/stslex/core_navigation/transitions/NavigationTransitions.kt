package com.stslex.core_navigation.transitions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
interface NavigationTransitions {
    val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)
    val exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)
    val popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)
    val popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)
}