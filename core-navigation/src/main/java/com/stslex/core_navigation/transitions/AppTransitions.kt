package com.stslex.core_navigation.transitions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
sealed class AppTransitions {

    object Enter : AppTransitions() {

        val AnimatedContentScope<NavBackStackEntry>.slideRightIntoContainer: EnterTransition
            get() = slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Right,
                animationSpec = animationSpec
            )

        val AnimatedContentScope<NavBackStackEntry>.slideUpIntoContainer: EnterTransition
            get() = slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Up,
                animationSpec = animationSpec
            )

        val AnimatedContentScope<NavBackStackEntry>.slideDownIntoContainer: EnterTransition
            get() = slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Down,
                animationSpec = animationSpec
            )
    }

    object Exit : AppTransitions() {

        val AnimatedContentScope<NavBackStackEntry>.slideLeftOutOfContainer: ExitTransition
            get() = slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Left,
                animationSpec = animationSpec
            )

        val AnimatedContentScope<NavBackStackEntry>.slideRightOutOfContainer: ExitTransition
            get() = slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Right,
                animationSpec = animationSpec
            )

        val AnimatedContentScope<NavBackStackEntry>.slideUpOutOfContainer: ExitTransition
            get() = slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Up,
                animationSpec = animationSpec
            )

        val AnimatedContentScope<NavBackStackEntry>.slideDownOutOfContainer: ExitTransition
            get() = slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Down,
                animationSpec = animationSpec
            )
    }

    companion object {

        @JvmStatic
        private val animationSpec: FiniteAnimationSpec<IntOffset> = tween(700)
    }
}