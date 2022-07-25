package com.jp.groupup.ui.screens.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jp.groupup.ui.components.ProfilePicture
import com.jp.groupup.ui.components.ProfileSizes
import com.jp.groupup.ui.theme.groupUpGradient


@Composable
fun ProfileItem(
    @DrawableRes profileImageId: Int,
    profileName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
){

    val borderColor = Brush.linearGradient(
        colors = groupUpGradient,
        start = Offset(x = 0f, y = 0f),
        end = Offset(x = 100f, y = 100f)
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePicture(
            imageId = profileImageId,
            contentDescription = null,
            size = ProfileSizes.large,
            modifier = Modifier
                .border(
                    shape = CircleShape,
                    border = BorderStroke(width = 2.dp, brush = borderColor)
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(bounded = false, radius = ProfileSizes.large / 2),
                    enabled = true,
                    onClickLabel = null,
                    onClick = onClick
                )
        )
        Text(text = profileName, style = textStyle, textAlign = TextAlign.Center)
    }
}

