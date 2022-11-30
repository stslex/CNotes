package com.stslex.feature_note_list.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class ButtonState {
    DEFAULT, EXPAND;

    fun click(): ButtonState = when (this) {
        DEFAULT -> EXPAND
        EXPAND -> DEFAULT
    }
}

@Composable
fun ExpandButton(
    modifier: Modifier = Modifier,
    painterResource: Int,
    textResource: Int,
    onExpandClick: () -> Unit,
) {
    BaseExpandButton(
        modifier = modifier,
        textResource = textResource,
        onExpandClick = onExpandClick,
        Icon = {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(id = painterResource),
                contentDescription = stringResource(id = textResource),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}

@Composable
fun ExpandButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    imagePressedVector: ImageVector,
    textResource: Int,
    onExpandClick: () -> Unit,
) {
    var isClicked by mutableStateOf(false)

    BaseExpandButton(
        modifier = modifier,
        textResource = textResource,
        onExpandClick = {
            isClicked = isClicked.not()
            onExpandClick()
        },
        Icon = {
            AnimatedVisibility(visible = isClicked) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    imageVector = imageVector,
                    contentDescription = stringResource(id = textResource)
                )
            }
            AnimatedVisibility(visible = isClicked.not()) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    imageVector = imagePressedVector,
                    contentDescription = stringResource(id = textResource)
                )
            }
        }
    )
}

@Composable
fun ExpandButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    textResource: Int,
    onExpandClick: () -> Unit,
) {
    BaseExpandButton(
        modifier = modifier,
        textResource = textResource,
        onExpandClick = onExpandClick,
        Icon = {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                imageVector = imageVector,
                contentDescription = stringResource(id = textResource)
            )
        }
    )
}

@Composable
private fun BaseExpandButton(
    modifier: Modifier = Modifier,
    textResource: Int,
    onExpandClick: () -> Unit,
    Icon: @Composable RowScope.() -> Unit
) {
    val buttonState = remember {
        mutableStateOf(ButtonState.DEFAULT)
    }

    var expandPendingJob: Job? = null
    LaunchedEffect(key1 = buttonState.value == ButtonState.EXPAND) {
        expandPendingJob = launch(Dispatchers.IO) {
            delay(4000L)
            buttonState.value = ButtonState.DEFAULT
        }
    }

    TextButton(
        modifier = modifier.padding(horizontal = 8.dp),
        onClick = {
            if (buttonState.value == ButtonState.EXPAND) {
                expandPendingJob?.cancel()
                onExpandClick()
            }
            buttonState.value = buttonState.value.click()
        },
        shape = ButtonDefaults.outlinedShape
    ) {
        Row {
            Icon()
            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp),
                visible = buttonState.value == ButtonState.EXPAND
            ) {
                Text(
                    text = stringResource(id = textResource),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}