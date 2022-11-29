package st.slex.feature_main.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.stslex.core_navigation.TopLevelDestination
import com.stslex.core_ui.components.ClearRippleTheme
import st.slex.feature_main.utils.MainScreenExt.appbarInsets
import st.slex.feature_main.utils.MainScreenExt.isSelected
import st.slex.feature_main.utils.MainScreenExt.topLevelDestinationList

@Composable
fun AppBottomBar(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    AppSurface {
        AppBottomNavigationBar(
            onNavigateToTopLevelDestination = onNavigateToTopLevelDestination,
            currentDestination = currentDestination
        )
    }
}

@Composable
private fun AppBottomNavigationBar(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar(
        modifier = Modifier.windowInsetsPadding(appbarInsets()),
        tonalElevation = 0.dp
    ) {
        topLevelDestinationList.forEach { destination ->
            val selected = currentDestination.isSelected(destination)
            AppBarBottomItem(
                selected = selected,
                destination = destination
            ) {
                onNavigateToTopLevelDestination(destination)
            }
        }
    }
}

@Composable
private fun RowScope.AppBarBottomItem(
    selected: Boolean,
    destination: TopLevelDestination,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = appBarBottomItemIcon(destination, selected),
        label = appBarBottomItemLabel(destination),
        alwaysShowLabel = false
    )
}

private fun appBarBottomItemIcon(
    destination: TopLevelDestination,
    selected: Boolean
): @Composable () -> Unit = {
    Icon(
        imageVector = destination.icon(selected),
        contentDescription = null
    )
}

private fun appBarBottomItemLabel(
    destination: TopLevelDestination
): @Composable () -> Unit = {
    Text(stringResource(destination.iconTextId))
}

@Composable
private fun AppSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = Surface(
    modifier = modifier,
    color = MaterialTheme.colorScheme.surface,
    content = {
        CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
            content()
        }
    }
)