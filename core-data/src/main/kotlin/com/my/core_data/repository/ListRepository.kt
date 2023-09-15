package com.my.core_data.repository

import androidx.annotation.WorkerThread
import com.my.core_model.Pokemon
import kotlinx.coroutines.flow.Flow

interface ListRepository {

    @WorkerThread
    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>>
}