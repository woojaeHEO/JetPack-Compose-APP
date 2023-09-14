package com.example.jetpack_compose_app.screen.list

import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.my.core_data.repository.ListRepository
import com.my.core_model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    listRepository: ListRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    fun selectTab(@StringRes tab: Int) {
        _selectedTab.value = tab
    }

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val pokemonList: Flow<List<Pokemon>> = pokemonFetchingIndex.flatMapLatest { page ->
        listRepository.fetchPokemonList(
            page = page,
            onStart = { _isLoading.value = true },
            onComplete = { _isLoading.value = false },
            onError = { }
        )
    }

    @MainThread
    fun fetchNextPokemonList() {
        if (!isLoading.value) {
            pokemonFetchingIndex.value++
        }
    }
}