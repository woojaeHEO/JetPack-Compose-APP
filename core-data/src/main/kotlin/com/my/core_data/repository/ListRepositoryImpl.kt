package com.my.core_data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.my.core_database.PokemonDao
import com.my.core_database.entity.mapper.asDomain
import com.my.core_database.entity.mapper.asEntity
import com.my.core_network.Dispatcher
import com.my.core_network.PokedexAppDispatchers
import com.my.core_network.service.PokedexClient
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
  private val pokedexClient: PokedexClient,
  private val pokemonDao: PokemonDao,
  @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): ListRepository {

  @WorkerThread
  override fun fetchPokemonList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {

    var pokemons = pokemonDao.getPokemonList(page).asDomain()
    Log.d(">>>", "fetchPokemonList: ${pokemons.size}")
    if ( pokemons.isEmpty() ) {

      val response = pokedexClient.fetchPokemonList(page = page)
      response.suspendOnSuccess {
        Log.d(">>>", "fetchPokemonList: ${data.results.size}")
        pokemons = data.results
        pokemons.forEach { pokemon -> pokemon.page = page }
        pokemonDao.insertPokemonList(pokemons.asEntity())
        emit(pokemonDao.getAllPokemonList(page).asDomain())
      }
    } else {
      emit(pokemonDao.getAllPokemonList(page).asDomain())
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}