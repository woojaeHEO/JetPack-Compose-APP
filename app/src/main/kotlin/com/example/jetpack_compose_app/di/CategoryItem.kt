package com.example.jetpack_compose_app.di

import androidx.compose.ui.graphics.Color
import com.example.jetpack_compose_app.ui.theme.Blue300
import com.example.jetpack_compose_app.ui.theme.Blue500
import com.example.jetpack_compose_app.ui.theme.Green300
import com.example.jetpack_compose_app.ui.theme.Green500
import com.example.jetpack_compose_app.ui.theme.Red300
import com.example.jetpack_compose_app.ui.theme.Red500
import com.example.jetpack_compose_app.ui.theme.Yellow300
import com.example.jetpack_compose_app.ui.theme.Yellow500

data class CategoryItem(
    val title: String,
    val iconUrl: String,
    val startColor: Color,
    val endColor: Color,
) {
    companion object {
        val pokedex = CategoryItem(
            title = "Pokedex",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/pokeball.png",
            startColor = Red300,
            endColor = Red500,
        )

        val moves = CategoryItem(
            title = "Moves",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/electric.png",
            startColor = Yellow300,
            endColor = Yellow500,
        )

        val evolutions = CategoryItem(
            title = "Evolutions",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/dna.png",
            startColor = Green300,
            endColor = Green500,
        )

        val locations = CategoryItem(
            title = "Locations",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/location.png",
            startColor = Blue300,
            endColor = Blue500,
        )
    }
}
