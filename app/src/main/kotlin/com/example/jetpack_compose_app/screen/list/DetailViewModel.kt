package com.example.jetpack_compose_app.screen.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.my.core_data.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    detailRepository: DetailRepository
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val pokemonInfoSharedFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1)
    val pokemonInfoFlow = pokemonInfoSharedFlow.flatMapLatest {
        detailRepository.fetchPokemonInfo(
            name = it,
            onComplete = { _isLoading.value = false },
            onError = {}
        )
    }

    fun loadPokemonInfo(pokemonName: String) = pokemonInfoSharedFlow.tryEmit(pokemonName)
}