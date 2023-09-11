package com.example.jetpack_compose_app.screen.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_app.di.VideoItem

@Composable
fun VideoRow(
    modifier: Modifier,
    videoList: ArrayList<VideoItem>,
    onVideoClick: (Int, VideoItem) -> Unit,
    onClick: (Int, VideoItem) -> Unit
) {

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp)
    ) {

        itemsIndexed(videoList) { index, video ->

            VideoItem(
                modifier = modifier,
                video = video,
                onVideoClick = { onVideoClick(index, video) },
                onClick = { onClick(index, video) }
            )
        }
    }
}