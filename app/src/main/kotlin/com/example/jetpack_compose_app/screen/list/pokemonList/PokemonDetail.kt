package com.example.jetpack_compose_app.screen.list.pokemonList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_app.screen.list.DetailViewModel
import com.example.jetpack_compose_app.screen.list.utils.PokemonTypeUtils
import com.example.jetpack_compose_app.ui.theme.Gray2B292B
import com.my.core_model.PokemonInfo
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun PokemonDetail(
    pokemonName: String,
    imageUrl: String,
    viewModel: DetailViewModel,
    pressOnBack: () -> Unit = {}
) {

    LaunchedEffect(key1 = pokemonName) {
        viewModel.loadPokemonInfo(pokemonName)
    }

    val pokemonInfo: PokemonInfo? by viewModel.pokemonInfoFlow.collectAsState(initial = null)
    Log.i("gustn", "PokemonDetail: $pokemonInfo")
    pokemonInfo?.let { info ->

        PokemonDetailBody(
            pokemonInfo = info,
            imageUrl = imageUrl,
            pressOnBack = pressOnBack
        )
    }
}

@Composable
private fun PokemonDetailBody(
    pokemonInfo: PokemonInfo,
    imageUrl: String,
    pressOnBack: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
            .background(Gray2B292B)
    ) {

        val colors = listOf<Color>(
            PokemonTypeUtils.getTypeColor(pokemonInfo.types.firstOrNull()?.type?.name ?: ""),
            PokemonTypeUtils.getTypeColor(pokemonInfo.types.lastOrNull()?.type?.name ?: "")
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(bottomStartPercent = 20, bottomEndPercent = 20))
                .background(
                    brush = Brush.verticalGradient(colors = colors),
                    shape = RectangleShape,
                    alpha = 0.9f
                )
        ) {
            Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = pressOnBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Pokedex",
                        color = Color.White,
                    )

                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        text = pokemonInfo.getIdString(),
                        color = Color.White
                    )
                }

                CoilImage(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    imageModel = { imageUrl },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp, bottom = 12.dp),
            text = pokemonInfo.name,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
        
        LazyRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {

            itemsIndexed(pokemonInfo.types) { index, type ->

                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 100))
                        .background(PokemonTypeUtils.getTypeColor(type.type.name))
                        .padding(start = 30.dp, end = 30.dp, top = 3.dp, bottom = 3.dp),
                    text = type.type.name,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}