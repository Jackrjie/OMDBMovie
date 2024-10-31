package com.jackrjie.omdbmovie.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jackrjie.omdbmovie.data.local.MovieDatabase
import com.jackrjie.omdbmovie.data.local.MovieEntity
import com.jackrjie.omdbmovie.data.remote.MovieAPI
import com.jackrjie.omdbmovie.data.remote.MovieRemoteMediator
import com.jackrjie.omdbmovie.domain.repository.MovieRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI,
    private val database: MovieDatabase
) : MovieRepository {

    override fun getMovies(search: String): Pager<Int, MovieEntity> {
        val pagingSourceFactory = { database.dao().getMovies("%$search%") }

        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 5, enablePlaceholders = false),
            remoteMediator = MovieRemoteMediator(search, movieAPI, database),
            pagingSourceFactory = pagingSourceFactory
        )
    }
}