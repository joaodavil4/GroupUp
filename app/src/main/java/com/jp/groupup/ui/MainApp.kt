package com.jp.groupup.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.jp.groupup.ui.navigation.AppDrawer
import com.jp.groupup.ui.navigation.MainNavGraph
import androidx.compose.material3.ModalNavigationDrawer

import com.jp.groupup.ui.screens.home.HomeScreen
import com.jp.groupup.ui.theme.GroupUpTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(){
    GroupUpTheme {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val openDrawer: () -> Unit = { coroutineScope.launch { drawerState.open() } }
        val closeDrawer: () -> Unit = { coroutineScope.launch { drawerState.close() } }
        if (drawerState.isOpen) {
            BackHandler { closeDrawer() }
        }
        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    navController = navController,
                    closeDrawer = closeDrawer
                )
            },
            drawerState = drawerState,
            gesturesEnabled = true
        ) {
            MainNavGraph(navController, openDrawer)
        }
    }
}