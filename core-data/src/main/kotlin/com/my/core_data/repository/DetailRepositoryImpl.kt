package com.my.core_data.repository

import androidx.annotation.WorkerThread
import com.my.core_database.PokemonInfoDao
import com.my.core_database.entity.mapper.asDomain
import com.my.core_database.entity.mapper.asEntity
import com.my.core_network.Dispatcher
import com.my.core_network.PokedexAppDispatchers
import com.my.core_network.model.mapper.ErrorResponseMapper
import com.my.core_network.service.PokedexClient
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonInfoDao: PokemonInfoDao,
    @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): DetailRepository {

    @WorkerThread
    override fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
        if ( pokemonInfo == null ) {

            val response = pokedexClient.fetchPokemonInfo(name = name)
            response.suspendOnSuccess {
                pokemonInfoDao.insertPokemonInfo(data.asEntity())
                emit(data)
            }
                .onError {
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                .onException { onError(message) }
        } else {
            emit(pokemonInfo.asDomain())
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}