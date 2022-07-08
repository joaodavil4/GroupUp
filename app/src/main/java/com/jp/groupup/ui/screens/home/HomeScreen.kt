package com.jp.groupup.ui.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.jp.groupup.R
import com.jp.groupup.ui.MainScreenScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openDrawer: () -> Unit,
){
    // A surface container using the 'background' color from the theme
    MainScreenScaffold(titleResId = R.string.app_name, openDrawer = { openDrawer() }) {

    }
}