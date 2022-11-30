package com.stslex.feature_note_list.ui.utils

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import com.stslex.core_model.model.NoteDynamicUI

object ColorExt {

    val NoteDynamicUI.textColor: @Composable () -> State<Color>
        get() = {
            colorAnimate(
                MaterialTheme.colorScheme.onSurface,
                MaterialTheme.colorScheme.onSurfaceVariant
            ).invoke()
        }

    val NoteDynamicUI.surfaceColor: @Composable () -> State<Color>
        get() = {
            colorAnimate(
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.surfaceVariant
            ).invoke()
        }

    private fun NoteDynamicUI.colorAnimate(
        unselected: Color,
        selected: Color
    ): @Composable () -> State<Color> = {
        animateColorAsState(
            targetValue = if (isSelect().value) selected else unselected,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 50,
                easing = LinearOutSlowInEasing
            )
        )
    }
}