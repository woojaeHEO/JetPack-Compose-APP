package com.example.jetpack_compose_app.screen.list.pokemonList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack_compose_app.screen.list.utils.NetworkImage
import com.example.jetpack_compose_app.ui.custom.StaggeredVerticalGrid
import com.my.core_model.Pokemon

@Composable
fun HomePokemonList(
    modifier: Modifier = Modifier,
    pokemonList: List<Pokemon>,
    selectPokemon: (Pokemon) -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            pokemonList.forEach { pokemon ->
                key( pokemon.name ) {
                    HomePokemon(
                        pokemon = pokemon,
                        selectPokemon = selectPokemon
                    )
                }
            }
        }
    }
}

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
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout {
            val (image, name) = createRefs()

            NetworkImage(
                url = pokemon.url,
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
            )

            Text(
                modifier = Modifier
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    },
                text = pokemon.name,
                style = MaterialTheme.typography.h1
            )
        }
    }
}