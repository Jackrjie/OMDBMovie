package com.jackrjie.omdbmovie.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.jackrjie.omdbmovie.data.local.MovieDatabase
import com.jackrjie.omdbmovie.data.local.MovieEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val title: String,
    private val api: MovieAPI,
    private val db: MovieDatabase,
): RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}