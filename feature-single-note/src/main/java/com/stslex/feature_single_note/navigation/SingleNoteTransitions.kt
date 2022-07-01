package com.stslex.feature_single_note.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import com.stslex.core_navigation.transitions.AppTransitions.Enter.slideDownIntoContainer
import com.stslex.core_navigation.transitions.AppTransitions.Exit.slideUpOutOfContainer
import com.stslex.core_navigation.transitions.NavigationTransitions

@OptIn(ExperimentalAnimationApi::class)
object SingleNoteTransitions : NavigationTransitions {

    override val enterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
        get() = { slideDownIntoContainer }

    override val exitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
        get() = { slideUpOutOfContainer }

    override val popEnterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
        get() = enterTransition

    override val popExitTransition: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
        get() = exitTransition
}