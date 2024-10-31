package com.jackrjie.omdbmovie.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jackrjie.omdbmovie.data.local.MovieDatabase
import com.jackrjie.omdbmovie.data.local.MovieEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val title: String,
    private val api: MovieAPI,
    private val db: MovieDatabase
): RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    if (lastItem == null) {
//                        1
//                    } else {
//                        (lastItem.imd  / state.config.pageSize) + 1
//                    }
                    state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    state.pages.size + 1
                }
            }

            val movies = api.getMovies(
                title = title,
                page = loadKey
            )
            val response = movies.toMovieEntities()

            db.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    db.dao().clearAll()
                }
                db.dao().upsertAll(response)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}