package com.jp.groupup.ui.navigation

sealed class GroupUpScreen(val route: String) {
    object Home : GroupUpScreen("Home")
    object Discover : GroupUpScreen("Discover")
    object Create : GroupUpScreen("Create")
    object Inbox : GroupUpScreen("Inbox")
    object Me : GroupUpScreen("Me")
    object Profile : GroupUpScreen("Profile")
}