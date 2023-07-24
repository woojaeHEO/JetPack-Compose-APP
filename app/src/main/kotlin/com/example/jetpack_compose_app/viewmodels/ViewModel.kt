package com.example.jetpack_compose_app.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class ViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle,
//    repository: Repository
//) : ViewModel() {
//
//    private var queryString: String? = savedStateHandle["plantName"]
//
//    val plantPictures = repository.getSearchResultStream(queryString ?: "").cachedIn(viewModelScope)
//}