package com.jp.groupup.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp.groupup.R
import com.jp.groupup.domain.model.CalendarTimestamp
import com.jp.groupup.ui.MainScreenScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openDrawer: () -> Unit,
){
    val viewModel = hiltViewModel<HomeViewModel>()
    viewModel.loadData()

    val iHaveItems = viewModel.timeStamps

    // A surface container using the 'background' color from the theme
    MainScreenScaffold(titleResId = R.string.app_name, openDrawer = { openDrawer() }) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            iHaveContent(iHaveItems)
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun iHaveContent(iHaveItems: SnapshotStateList<CalendarTimestamp>) {
    Text(
        text = stringResource(id = R.string.i_have),
        fontWeight = FontWeight.Bold,
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        items(iHaveItems.size){
            SuggestionChip(
                label = { Text(text = iHaveItems[it].tag) },
                onClick = { },
            )
        }
    }
}