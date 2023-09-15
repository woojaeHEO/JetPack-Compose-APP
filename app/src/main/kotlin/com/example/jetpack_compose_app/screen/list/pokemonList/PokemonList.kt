package com.example.jetpack_compose_app.screen.list.pokemonList

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack_compose_app.R
import com.example.jetpack_compose_app.screen.list.ListViewModel
import com.my.core_model.Pokemon

@Composable
fun PokemonList(
    viewModel: ListViewModel,
    selectPokemon: (Pokemon) -> Unit
) {
    val pokemonList: List<Pokemon> by viewModel.pokemonList.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading
    val selectedTab = NavigationTab.getTab(viewModel.selectedTab.value)
    val tabs = NavigationTab.values()



    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            backgroundColor = Color.Red,
            topBar = {  },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            },
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color.Red,
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    tabs.forEach { tab ->
                        BottomNavigationItem(
                            icon = { Icon(imageVector = tab.icon , contentDescription = null) },
                            label = { Text(text = stringResource(id = tab.title), color = Color.White) },
                            selected = tab == selectedTab,
                            onClick = { viewModel.selectTab(tab.title) },
                            selectedContentColor = LocalContentColor.current,
                            unselectedContentColor = LocalContentColor.current
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            Crossfade( selectedTab, label = "") { destination ->
                when ( destination ) {
                    NavigationTab.HOME -> HomePokemonList(modifier, pokemonList, selectPokemon) { viewModel.fetchNextPokemonList() }
                    NavigationTab.LIST -> HomePokemonList(modifier, pokemonList, selectPokemon) { viewModel.fetchNextPokemonList() }
                    NavigationTab.FAVORITE -> HomePokemonList(modifier, pokemonList, selectPokemon) { viewModel.fetchNextPokemonList() }
                }
            }
        }
        if ( isLoading ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs( progress ) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}

enum class NavigationTab(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    HOME(R.string.menu_home, Icons.Filled.Home),
    LIST(R.string.menu_list, Icons.Filled.List),
    FAVORITE(R.string.menu_favorite, Icons.Filled.Favorite);

    companion object {
        fun getTab(@StringRes resource: Int): NavigationTab {
            return when ( resource ) {
                R.string.menu_list -> LIST
                R.string.menu_favorite -> FAVORITE
                else -> HOME
            }
        }
    }
}