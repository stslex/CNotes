package com.stslex.feature_single_note.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.accompanist.navigation.animation.composable
import com.stslex.core_navigation.SingleNoteDestination
import com.stslex.feature_single_note.ui.SingleNoteRoute
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.singleNoteGraph(popBackStack: () -> Unit) {
    composable(
        route = "${SingleNoteDestination.route}/{${SingleNoteDestination.noteIdArg}}",
        arguments = listOf(navArgument(SingleNoteDestination.noteIdArg) {
            type = NavType.IntType
        }),
        deepLinks = listOf(navDeepLink {
            uriPattern =
                "app://${SingleNoteDestination.route}/{${SingleNoteDestination.noteIdArg}}"
        }),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Down,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Up,
                animationSpec = tween(700)
            )
        }
    ) {
        val noteId: Int =
            it.arguments?.getInt(SingleNoteDestination.noteIdArg) ?: -1
        SingleNoteRoute(
            viewModel = getViewModel(parameters = { parametersOf(noteId) }),
            popBackStack = popBackStack
        )
    }
}