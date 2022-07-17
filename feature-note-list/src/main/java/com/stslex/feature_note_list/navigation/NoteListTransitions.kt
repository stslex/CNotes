package com.stslex.feature_note_list.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import com.stslex.core_navigation.destinations.PhoneNumberDestination
import com.stslex.core_navigation.destinations.ProfileDestination
import com.stslex.core_navigation.destinations.TodoDestination
import com.stslex.core_navigation.transitions.AppTransitions.Enter.slideRightIntoContainer
import com.stslex.core_navigation.transitions.AppTransitions.Enter.slideUpIntoContainer
import com.stslex.core_navigation.transitions.AppTransitions.Exit.slideDownOutOfContainer
import com.stslex.core_navigation.transitions.AppTransitions.Exit.slideLeftOutOfContainer
import com.stslex.core_navigation.transitions.NavigationTransitions

@OptIn(ExperimentalAnimationApi::class)
object NoteListTransitions : NavigationTransitions {

    override val enterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
        get() = {
            when (initialState.destination.route) {
                TodoDestination.route -> slideRightIntoContainer
                ProfileDestination.route, PhoneNumberDestination.route -> slideUpIntoContainer
                else -> null
            }
        }

    override val exitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
        get() = {
            when (targetState.destination.route) {
                TodoDestination.route -> slideLeftOutOfContainer
                ProfileDestination.route -> slideDownOutOfContainer
                else -> null
            }
        }

    override val popEnterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
        get() = enterTransition

    override val popExitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
        get() = exitTransition
}