package com.example.feature_auth_phonenumber.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import com.stslex.core_navigation.destinations.AuthCodeDestination
import com.stslex.core_navigation.destinations.NoteListDestination
import com.stslex.core_navigation.destinations.ProfileDestination
import com.stslex.core_navigation.transitions.AppTransitions.Enter.slideDownIntoContainer
import com.stslex.core_navigation.transitions.AppTransitions.Enter.slideRightIntoContainer
import com.stslex.core_navigation.transitions.AppTransitions.Exit.slideLeftOutOfContainer
import com.stslex.core_navigation.transitions.AppTransitions.Exit.slideUpOutOfContainer
import com.stslex.core_navigation.transitions.NavigationTransitions

@ExperimentalAnimationApi
object AuthPhoneTransitions : NavigationTransitions {

    override val enterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
        get() = {
            when (initialState.destination.route) {
                NoteListDestination.destination, ProfileDestination.route -> slideDownIntoContainer
                AuthCodeDestination.route -> slideRightIntoContainer
                else -> null
            }
        }

    override val exitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
        get() = {
            when (targetState.destination.route) {
                NoteListDestination.destination, ProfileDestination.route -> slideUpOutOfContainer
                AuthCodeDestination.route -> slideLeftOutOfContainer
                else -> null
            }
        }

    override val popEnterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
        get() = enterTransition

    override val popExitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
        get() = exitTransition
}