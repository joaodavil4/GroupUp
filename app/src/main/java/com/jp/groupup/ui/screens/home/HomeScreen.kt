package com.jp.groupup.ui.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.jp.groupup.R
import com.jp.groupup.ui.MainScreenScaffold
import com.jp.groupup.ui.navigation.GroupUpScreen
import com.jp.groupup.ui.theme.GroupUpTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
){
    val navItemState = rememberSaveable { mutableStateOf(GroupUpScreen.Home.route) }

    GroupUpTheme {
        // A surface container using the 'background' color from the theme
        MainScreenScaffold(
            titleResId = R.string.app_name,
            floatingActionButton = {
                if(navItemState.value == GroupUpScreen.Home.route){
                    ExtendedFloatingActionButton(
                        text = { Text("Schedule")},
                        icon = { Icon(
                            Icons.Default.DateRange,
                            null,
                        )},
                        onClick = { /*TODO*/ }
                    )
                }
            },
        ) {
            Surface(modifier = Modifier.fillMaxSize()
                .padding(it)) {
                Crossfade(targetState = navItemState) { navType ->
                    when (navType.value) {
                        GroupUpScreen.Home.route -> HomeScreen ()
                        GroupUpScreen.Profile.route -> HomeScreen ()
                        else -> HomeScreen ()
                    }
                }
            }
        }
    }



}

@Composable
fun HomeScreen(){

}
