package com.jp.groupup.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp.groupup.R
import com.jp.groupup.ui.MainScreenScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openDrawer: () -> Unit,
){
    val viewModel = hiltViewModel<HomeViewModel>()
    viewModel.loadData()

    // A surface container using the 'background' color from the theme
    MainScreenScaffold(titleResId = R.string.app_name, openDrawer = { openDrawer() }) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(text = stringResource(id = R.string.i_have))


        }

    }
}