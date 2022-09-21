package com.jp.groupup.ui.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp.groupup.R
import com.jp.groupup.domain.model.CalendarTimestamp
import com.jp.groupup.domain.model.Game
import com.jp.groupup.domain.model.Profile
import com.jp.groupup.ui.MainScreenScaffold
import com.jp.groupup.ui.navigation.GroupUpScreen
import com.jp.groupup.ui.screens.home.components.GameItem
import com.jp.groupup.ui.screens.home.components.ProfileItem
import com.jp.groupup.ui.theme.GroupUpTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    openDrawer: () -> Unit,
){
    val navItemState = rememberSaveable { mutableStateOf(GroupUpScreen.Home.route) }

    GroupUpTheme {
        // A surface container using the 'background' color from the theme
        MainScreenScaffold(
            titleResId = R.string.app_name,
            openDrawer = { openDrawer() },
            floatingActionButton = {
                if(navItemState.value == GroupUpScreen.Home.route){
                    ExtendedFloatingActionButton(
                        text = { Text("Schedule")},
                        icon = { Icon(
                            Icons.Default.DateRange,
                            null,
                        )},
                        onClick = { /*TODO*/ }
                    )
                }
            },
            bottomNavigationBar = { AppBottomNavigation(navItemState = navItemState)},
        ) {
            Surface(modifier = Modifier.fillMaxSize()
                .padding(it)) {
                Crossfade(targetState = navItemState) { navType ->
                    when (navType.value) {
                        GroupUpScreen.Home.route -> HomeScreen ()
                        GroupUpScreen.Profile.route -> HomeScreen ()
                        else -> HomeScreen ()
                    }
                }
            }
        }
    }



}

@Composable
fun HomeScreen(){
    val viewModel = hiltViewModel<HomeViewModel>()
    val iHaveItems = viewModel.timeStamps
    val games = listOf(Game("Overwatch"), Game("COD"))
    val friends = listOf(Profile("Pedrinho"), Profile("Acorda"))


    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        IHaveContent(iHaveItems, viewModel)
        GamesContent(games, viewModel)
        FriendsContent(friends, viewModel)
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


@Composable
fun GamesContent(
    games: List<Game>,
    viewModel: HomeViewModel,
) {
    Text(
        text = stringResource(id = R.string.games),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        itemsIndexed(games){ _, game ->
            GameItem(
                game = game,
                onGameClick = { viewModel.onClickGame(game) },
                selectedGame = viewModel.selectedGame.value?.equals(game) ?: false,
            )
        }
    }
}

@Composable
fun FriendsContent(
    friends: List<Profile>,
    viewModel: HomeViewModel,
) {
    Text(
        text = stringResource(id = R.string.friends),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        itemsIndexed(friends){ _, friend ->
            ProfileItem(
                profileImageId = R.drawable.ic_movie_placeholder,
                profileName = friend.name,
                onClick = { viewModel.onClickFriend(friend) },
                selectedProfile = viewModel.selectedFriends.contains(friend)
            )
        }
    }
}


/**BottomBar*/

@Composable
fun AppBottomNavigation(navItemState: MutableState<String>) {
    BottomAppBar() {

        IconButton(onClick = { navItemState.value == GroupUpScreen.Home.route}) {
            Icon(imageVector = Icons.Default.Home, contentDescription = null)
        }

        IconButton(onClick = { navItemState.value == GroupUpScreen.Profile.route}) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
        }
    }
}