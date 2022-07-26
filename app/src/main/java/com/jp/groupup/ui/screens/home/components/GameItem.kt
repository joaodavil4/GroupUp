package com.jp.groupup.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jp.groupup.R
import com.jp.groupup.domain.model.Game

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameItem(
    game: Game,
    selectedGame: Boolean,
    onGameClick: () -> Unit,
){
    ElevatedCard(
        modifier = Modifier
            .width(120.dp)
            .wrapContentHeight()
            .clickable { onGameClick() },
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .width(180.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier
                .width(120.dp)
                .height(100.dp),
            ){
                Image(
                    painter = painterResource(R.drawable.ic_movie_placeholder),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                if(selectedGame){
                    Icon(
                        Icons.Default.CheckCircle,
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.BottomEnd),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }

            }


            Text(
                text = game.name,
                style = typography.bodySmall,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }

}

@Preview
@Composable
fun PreviewGameItem(){
   // GameItem()
}