package com.rachmanm.tvshow.ui.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rachmanm.tvshow.di.ResultState
import com.rachmanm.tvshow.domain.model.Show
import com.rachmanm.tvshow.domain.usecase.GetTvShowListUseCase
import kotlinx.coroutines.launch

class ListScreenViewModel(
    private val getTvShowListUseCase: GetTvShowListUseCase
) : ViewModel() {

    private val _showListState = MutableLiveData<ResultState<List<Show>>>()
    val showListState: LiveData<ResultState<List<Show>>> get() = _showListState

    init {
        loadShows()
    }

    fun loadShows() {
        viewModelScope.launch {
            getTvShowListUseCase().collect { state ->
                _showListState.value = state
            }
        }
    }

}