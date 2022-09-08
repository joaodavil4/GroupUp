package com.jp.groupup.ui.navigation

sealed class GroupUpScreen(val route: String) {
    object Home : GroupUpScreen("Home")
    object Game : GroupUpScreen("Game")
    object Profile : GroupUpScreen("Profile")
}