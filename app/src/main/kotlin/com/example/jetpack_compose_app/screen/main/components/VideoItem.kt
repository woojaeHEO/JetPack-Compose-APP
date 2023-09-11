package com.example.jetpack_compose_app.screen.main.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_app.di.VideoItem
import com.example.jetpack_compose_app.ui.theme.Gray2D2D2D
import com.example.jetpack_compose_app.ui.theme.Gray888888
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun VideoItem(
    modifier: Modifier,
    video: VideoItem,
    onVideoClick: () -> Unit,
    onClick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .width(250.dp)
            .padding(10.dp)
            .clickable { onClick() }
    ) {

        Box(
            modifier = Modifier.clickable { onVideoClick() },
            contentAlignment = Alignment.Center
        ) {

            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clip(RoundedCornerShape(8.dp)),
                imageModel = { video.imageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )

            Surface(
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        width = 3.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(30.dp)
                    ),
                color = Color.Transparent
            ) {
                Icon(
                    Icons.Rounded.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp),
                )
            }
        }

        Text(
            text = video.title,
            color = Gray2D2D2D,
            style = MaterialTheme.typography.titleSmall
        )

        Text(
            text = "${video.year} | ${video.category} | ${video.details}",
            color = Gray888888,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}