package com.stslex.feature_auth_code.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import com.stslex.core_navigation.destinations.PhoneNumberDestination
import com.stslex.core_navigation.destinations.ProfileDestination
import com.stslex.core_navigation.transitions.AppTransitions.Enter.slideRightIntoContainer
import com.stslex.core_navigation.transitions.AppTransitions.Exit.slideRightOutOfContainer
import com.stslex.core_navigation.transitions.AppTransitions.Exit.slideUpOutOfContainer
import com.stslex.core_navigation.transitions.NavigationTransitions

@ExperimentalAnimationApi
object AuthCodeTransitions : NavigationTransitions {

    override val enterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
        get() = {
            when (initialState.destination.route) {
                PhoneNumberDestination.route -> slideRightIntoContainer
                else -> null
            }
        }

    override val exitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
        get() = {
            when (targetState.destination.route) {
                ProfileDestination.route -> slideUpOutOfContainer
                PhoneNumberDestination.route -> slideRightOutOfContainer
                else -> null
            }
        }

    override val popEnterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
        get() = enterTransition

    override val popExitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
        get() = exitTransition
}