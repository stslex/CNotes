package com.example.feature_note_list.ui.fab

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.IntSize
import com.stslex.core_model.model.NoteDynamicUI

@OptIn(ExperimentalAnimationApi::class)
inline fun animatedButtonContent(
    snapshotStateList: SnapshotStateList<NoteDynamicUI>,
    crossinline targetContent: @Composable () -> Unit,
    crossinline content: @Composable () -> Unit
): @Composable () -> Unit = {
    AnimatedContent(
        targetState = snapshotStateList.isNotEmpty(),
        transitionSpec = transitionContentButtonSpec
    ) { targetState ->
        if (targetState) targetContent() else content()
    }
}

@OptIn(ExperimentalAnimationApi::class)
val transitionContentButtonSpec: AnimatedContentScope<Boolean>.() -> ContentTransform
    get() = {
        fadeIn(
            animationSpec = tween(
                500,
                150
            )
        ) with fadeOut(
            animationSpec = tween(500)
        ) using SizeTransform { initialSize, targetSize ->
            if (targetState) {
                keyframes {
                    IntSize(targetSize.width, initialSize.height) at 150
                    durationMillis = 300
                }
            } else {
                keyframes {
                    IntSize(initialSize.width, targetSize.height) at 150
                    durationMillis = 300
                }
            }
        }
    }