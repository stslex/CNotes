package com.stslex.feature_todo.ui

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.core_ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

/**
 * Just screen for trying
 * some cool animations
 * */
@Composable
fun ToDoContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Animation()
    }
}

@Composable
private fun Animation(
    modifier: Modifier = Modifier,
) {
    val deltaSize = remember { mutableStateOf(0f) }
    val color = MaterialTheme.colorScheme.onBackground
    val circleNum = 6
    val circleConst = 360f / circleNum
    val circleSpeed = 0.01f
    val ellipseSpeed = 3.375f
    val ellipseRadiusDeltaStart = 7
    val ellipseRadius = 4 * 20
    val circleRadius = 100
    Canvas(
        modifier = modifier.wrapContentSize()
    ) {
        val currentTime = deltaSize.value
        val startOffset = Offset(
            x = (1 * ellipseRadiusDeltaStart * sin(currentTime) + sin(currentTime * ellipseSpeed)) * ellipseRadius,
            y = (5 * ellipseRadiusDeltaStart * sin(currentTime) + cos(currentTime * ellipseSpeed)) * ellipseRadius
        )
        for (n in 1 until circleNum) {
            val currentOffset = Offset(
                x = startOffset.x + circleRadius * cos(currentTime * n * circleConst * circleSpeed),
                y = startOffset.y + circleRadius * sin(currentTime * n * circleConst * circleSpeed)
            )
            drawCircle(
                center = currentOffset,
                color = color,
                radius = 3f
            )
        }
    }

    LaunchedEffect(true) {
        launch {
            while (true) {
                deltaSize.value = deltaSize.value + 0.001f
                delay(1)
            }
        }
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true, showBackground = true, device = "id:pixel_6"
)
@Composable
fun ToDoContentPreview() {
    AppTheme(
        isSystemInDarkTheme(),
        dynamicColor = true
    ) {
        ToDoContent()
    }

}