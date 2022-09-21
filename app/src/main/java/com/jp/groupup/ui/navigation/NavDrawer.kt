package com.jp.groupup.ui.navigation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jp.groupup.R
import com.jp.groupup.ui.theme.GroupUpTheme

@Composable
fun AppDrawer(
    navController: NavController,
    closeDrawer: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: GroupUpScreen.Home.route
    val newFeatureEnabled = true
       // activityViewModel<MainViewModel>().newFeatureEnabled.collectAsStateWhenStarted(false).value
    AppDrawer(
        currentRoute = currentRoute,
        newFeatureEnabled = newFeatureEnabled,
        navController = navController,
        closeDrawer = closeDrawer
    )
}

@Composable
fun AppDrawer(
    currentRoute: String,
    newFeatureEnabled: Boolean,
    navController: NavController,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val onEntrySelected: (String) -> Unit = {
        closeDrawer()
        if (currentRoute != it) {
            navController.navigate(it) {
                launchSingleTop = true
                restoreState = true
                popUpTo(GroupUpScreen.Home.route) {
                    saveState = true
                }
            }
        }
    }
    Column(modifier = modifier.fillMaxSize()) {
        MainEntry.values().filter {
            newFeatureEnabled //|| it != MainEntry.NEW_FEATURE
        }.forEach { entry ->
            DrawerItem(
                icon = entry.icon,
                label = stringResource(entry.labelResId),
                isSelected = currentRoute == entry.route,
                action = { onEntrySelected(entry.route) }
            )
        }
    }
}

@Composable
private fun DrawerItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    imageVector = icon,
                    contentDescription = null,
                    alpha = if (isSelected) 1f else 0.6f
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = label,
                    color = textIconColor
                )
            }
        }
    }
}

private enum class MainEntry(
    @StringRes val labelResId: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.home_title, Icons.Filled.Home, GroupUpScreen.Home.route),
    ABOUT(R.string.about_title, Icons.Filled.Info, GroupUpScreen.Profile.route)
    //SETTINGS(R.string.settings_title, Icons.Filled.Settings, ROUTE_SETTINGS),
}

@Preview("App Drawer")
@Preview("App Drawer (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    GroupUpTheme {
        AppDrawer(
            currentRoute = GroupUpScreen.Home.route,
            newFeatureEnabled = true,
            navController = rememberNavController(),
            closeDrawer = { }
        )
    }
}