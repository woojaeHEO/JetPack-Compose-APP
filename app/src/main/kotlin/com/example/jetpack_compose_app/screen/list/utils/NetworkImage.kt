package com.example.jetpack_compose_app.screen.list.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    circularRevealEnabled: Boolean = false,
    contentScale: ContentScale = ContentScale.Crop
) {
    com.skydoves.landscapist.coil.CoilImage(
        imageModel = { url },
        modifier = modifier,
        imageOptions = ImageOptions(contentScale = contentScale),
        component = rememberImageComponent {

        },
        failure = {

        }
    )
}