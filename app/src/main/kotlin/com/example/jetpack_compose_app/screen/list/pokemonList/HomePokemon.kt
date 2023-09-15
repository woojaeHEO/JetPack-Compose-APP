package com.example.jetpack_compose_app.screen.list.pokemonList

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack_compose_app.screen.list.utils.NetworkImage
import com.example.jetpack_compose_app.ui.theme.Gray2B292B
import com.my.core_model.Pokemon

@Composable
fun HomePokemonList(
    modifier: Modifier = Modifier,
    pokemonList: List<Pokemon>,
    selectPokemon: (Pokemon) -> Unit,
    fetchNextList: () -> Unit
) {

    val scrollState = rememberLazyGridState()

    if ( pokemonList.isNotEmpty() && !scrollState.canScrollForward ) {
        fetchNextList()
    }
    
    LazyVerticalGrid(
        modifier = modifier
            .background(Gray2B292B),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        state = scrollState
    ) {

        items(pokemonList.size) { index ->

            HomePokemon(
                pokemon = pokemonList[index],
                selectPokemon = selectPokemon
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
private fun HomePokemon(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    selectPokemon: (Pokemon) -> Unit = {}
) {

    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = { selectPokemon(pokemon) }
            ),
        color = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        shape = RoundedCornerShape(14.dp)
    ) {
        ConstraintLayout {
            val (image, name) = createRefs()

            NetworkImage(
                url = pokemon.getImageUrl(),
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
                    .padding(10.dp)
            )

            Text(
                modifier = Modifier
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    },
                text = pokemon.name,
                style = MaterialTheme.typography.caption,
                color = Color.White
            )
        }
    }
}