package com.example.jetpack_compose_app.screen.list.utils

import androidx.compose.ui.graphics.Color
import com.example.jetpack_compose_app.ui.theme.Bug
import com.example.jetpack_compose_app.ui.theme.Dark
import com.example.jetpack_compose_app.ui.theme.Dragon
import com.example.jetpack_compose_app.ui.theme.Electric
import com.example.jetpack_compose_app.ui.theme.Fairy
import com.example.jetpack_compose_app.ui.theme.Fighting
import com.example.jetpack_compose_app.ui.theme.Fire
import com.example.jetpack_compose_app.ui.theme.Flying
import com.example.jetpack_compose_app.ui.theme.Ghost
import com.example.jetpack_compose_app.ui.theme.Grass
import com.example.jetpack_compose_app.ui.theme.GrayB1A5A5
import com.example.jetpack_compose_app.ui.theme.Ground
import com.example.jetpack_compose_app.ui.theme.Ice
import com.example.jetpack_compose_app.ui.theme.Poison
import com.example.jetpack_compose_app.ui.theme.Psychic
import com.example.jetpack_compose_app.ui.theme.Rock
import com.example.jetpack_compose_app.ui.theme.Steel
import com.example.jetpack_compose_app.ui.theme.Water

object PokemonTypeUtils {

    fun getTypeColor(type: String): Color {

        return when( type ) {
            "Fighting" -> Fighting
            "flying" -> Flying
            "poison" -> Poison
            "ground" -> Ground
            "rock" -> Rock
            "bug" -> Bug
            "ghost" -> Ghost
            "steel" -> Steel
            "fire" -> Fire
            "water" -> Water
            "grass" -> Grass
            "electric" -> Electric
            "psychic" -> Psychic
            "ice" -> Ice
            "dragon" -> Dragon
            "fairy" -> Fairy
            "dark" -> Dark
            else -> GrayB1A5A5
        }
    }
}