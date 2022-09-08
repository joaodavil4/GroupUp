package com.jp.groupup.ui.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jp.groupup.ui.screens.home.MainScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = GroupUpScreen.Home.route,
    ) {

        composable(route = GroupUpScreen.Home.route) {
            MainScreen()
        }
    }
}