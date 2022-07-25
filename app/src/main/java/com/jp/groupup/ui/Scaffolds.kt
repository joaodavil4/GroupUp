package com.jp.groupup.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@ExperimentalMaterial3Api
@Composable
fun MainScreenScaffold(
    @StringRes titleResId: Int,
    openDrawer: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(titleResId)) },
                navigationIcon = {
                    IconButton(onClick = { openDrawer() }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            )
        },
        content = content
    )
}