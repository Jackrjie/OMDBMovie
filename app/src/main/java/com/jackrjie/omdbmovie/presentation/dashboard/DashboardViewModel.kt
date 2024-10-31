package com.jackrjie.omdbmovie.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.jackrjie.omdbmovie.data.mappers.toMovie
import com.jackrjie.omdbmovie.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    useCase: GetMovieUseCase
) : ViewModel() {

    val moviePagingFlow = useCase
        .invoke("Marvel")
        .flow
        .map { pagingData ->
            pagingData.map { it.toMovie() }
        }
        .cachedIn(viewModelScope)
}