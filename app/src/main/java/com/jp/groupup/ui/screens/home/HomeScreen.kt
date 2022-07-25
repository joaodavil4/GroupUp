package com.jp.groupup.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp.groupup.R
import com.jp.groupup.domain.model.CalendarTimestamp
import com.jp.groupup.ui.MainScreenScaffold
import com.jp.groupup.ui.theme.GroupUpTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openDrawer: () -> Unit,
){
    val viewModel = hiltViewModel<HomeViewModel>()
    val iHaveItems = viewModel.timeStamps

    GroupUpTheme {
        // A surface container using the 'background' color from the theme
        MainScreenScaffold(titleResId = R.string.app_name, openDrawer = { openDrawer() }) {
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    IHaveContent(iHaveItems, viewModel)
                }
            }


        }
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IHaveContent(
    iHaveItems: SnapshotStateList<CalendarTimestamp>,
    viewModel: HomeViewModel,
) {
    Text(
        text = stringResource(id = R.string.i_have),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        itemsIndexed(iHaveItems){ _, timeStamp ->
            FilterChip(
                label = { Text(text = timeStamp.tag) },
                onClick = { viewModel.onClickTimeStamp(timeStamp) },
                selectedIcon = {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = ""
                    )
                },
                selected = viewModel.selectedTimeStamp.value?.equals(timeStamp) ?: false
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IWantContent(
    iHaveItems: SnapshotStateList<CalendarTimestamp>,
    viewModel: HomeViewModel,
) {
    Text(
        text = stringResource(id = R.string.i_want),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        itemsIndexed(iHaveItems){ _, timeStamp ->
            FilterChip(
                label = { Text(text = timeStamp.tag) },
                onClick = { viewModel.onClickTimeStamp(timeStamp) },
                selectedIcon = {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = ""
                    )
                },
                selected = viewModel.selectedTimeStamp.value?.equals(timeStamp) ?: false
            )
        }
    }
}