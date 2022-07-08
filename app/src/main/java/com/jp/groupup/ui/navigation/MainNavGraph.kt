package com.jp.groupup.ui.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jp.groupup.ui.screens.home.HomeScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = NavDestinations.HOME,
    ) {

        composable(route = NavDestinations.HOME) {
            HomeScreen(openDrawer)
        }
    }
}