package com.jackrjie.omdbmovie.domain.repository

import androidx.paging.PagingData
import com.jackrjie.omdbmovie.data.local.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(title: String): Flow<PagingData<MovieEntity>>
}