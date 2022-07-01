package com.example.feature_auth_code.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.feature_auth_code.ui.AuthCodeRoute
import com.google.accompanist.navigation.animation.composable
import com.stslex.core_navigation.destinations.AuthCodeDestination
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
        enterTransition = AuthCodeTransitions.enterTransition,
        exitTransition = AuthCodeTransitions.exitTransition
    ) {
        val verificationId: String = it.arguments?.getString(AuthCodeDestination.verificationIdArg) ?: ""
        AuthCodeRoute(
            popBackStack = popBackStack,
            openProfile = openProfile,
            viewModel = getViewModel(parameters = { parametersOf(verificationId) })
        )
    }
}