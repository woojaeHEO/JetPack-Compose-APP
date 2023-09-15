package com.example.jetpack_compose_app.screen.list

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpack_compose_app.screen.list.pokemonList.PokemonDetail
import com.example.jetpack_compose_app.screen.list.pokemonList.PokemonList
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Preview
@Composable
fun PokemonListScreen() {
    val navController = rememberNavController()

    val colors = MaterialTheme.colors
    val systemUiController = rememberSystemUiController()

    var statusBarColor by remember { mutableStateOf(colors.primaryVariant) }
    var navigationBarColor by remember { mutableStateOf(colors.primaryVariant) }

    val animatedStatusBarColor by animateColorAsState(
        targetValue = statusBarColor,
        animationSpec = tween(),
        label = ""
    )
    val animatedNavigationBarColor by animateColorAsState(
        targetValue = navigationBarColor,
        animationSpec = tween(),
        label = ""
    )

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {

        composable(NavScreen.Home.route) {

            PokemonList(
                viewModel = hiltViewModel(),
                selectPokemon = {

                    val encodedUrl = try {
                        URLEncoder.encode(it.getImageUrl(), StandardCharsets.UTF_8.toString())
                    } catch (e: UnsupportedEncodingException) {
                        ""
                    }

                    navController.navigate("${NavScreen.Details.route}/${it.name}/${encodedUrl}")
                }
            )

            LaunchedEffect(Unit) {
                statusBarColor = colors.primaryVariant
                navigationBarColor = colors.primaryVariant
            }
        }

        composable(
            route = NavScreen.Details.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.Details.argument0) { type = NavType.StringType }
            )
        ) { backStackEntry ->

            Log.i("gustn", "PokemonListScreen: backStackEntry")
            val name = backStackEntry.arguments?.getString(NavScreen.Details.argument0) ?: return@composable
            val imageUrl = backStackEntry.arguments?.getString(NavScreen.Details.argument1) ?: return@composable

            PokemonDetail(name, imageUrl, viewModel = hiltViewModel()) {
                navController.navigateUp()
            }

            LaunchedEffect(Unit) {
                statusBarColor = Color.Transparent
                navigationBarColor = Color.Transparent

            }
        }
    }

    LaunchedEffect(animatedStatusBarColor, animatedNavigationBarColor) {
        systemUiController.setStatusBarColor(animatedStatusBarColor)
        systemUiController.setNavigationBarColor(animatedNavigationBarColor)
    }
}

sealed class NavScreen(val route: String) {

    object Home: NavScreen("Home")

    object Details: NavScreen("Details") {

        const val routeWithArgument: String = "Details/{Name}/{ImageUrl}"
        const val argument0: String = "Name"
        const val argument1: String = "ImageUrl"
    }
}