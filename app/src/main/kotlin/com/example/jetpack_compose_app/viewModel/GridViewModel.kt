package com.example.jetpack_compose_app.viewModel

import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_app.di.GridItem
import com.example.jetpack_compose_app.di.GridRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(private val repository: GridRepository) : ViewModel() {

    fun getGridItems(): List<GridItem> { return repository.getGridItems() }
}