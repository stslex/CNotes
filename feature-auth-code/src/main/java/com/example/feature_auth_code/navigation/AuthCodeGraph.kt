package com.example.feature_auth_code.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.feature_auth_code.ui.AuthCodeRoute
import com.google.accompanist.navigation.animation.composable
import com.stslex.core_navigation.AuthCodeDestination
import com.stslex.core_navigation.PhoneNumberDestination
import com.stslex.core_navigation.ProfileDestination
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authCodeGraph(
    popBackStack: () -> Unit,
    openProfile: () -> Unit
) {
    composable(
        route = "${AuthCodeDestination.route}/{${AuthCodeDestination.verificationIdArg}}",
        arguments = listOf(
            navArgument(AuthCodeDestination.verificationIdArg) {
                type = NavType.StringType
            }
        ),
        enterTransition = {
            when (initialState.destination.route) {
                PhoneNumberDestination.route ->
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                else -> null
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                ProfileDestination.route ->
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                PhoneNumberDestination.route -> slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
                else -> null
            }
        }
    ) {
        val verificationId: String =
            it.arguments?.getString(AuthCodeDestination.verificationIdArg) ?: ""
        AuthCodeRoute(
            popBackStack = popBackStack,
            openProfile = openProfile,
            viewModel = getViewModel(parameters = { parametersOf(verificationId) })
        )
    }
}