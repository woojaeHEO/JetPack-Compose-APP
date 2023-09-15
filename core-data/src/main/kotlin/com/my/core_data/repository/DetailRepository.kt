package com.my.core_data.repository

import androidx.annotation.WorkerThread
import com.my.core_model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    @WorkerThread
    fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<PokemonInfo>
}