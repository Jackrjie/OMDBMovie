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
                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    } else {
                        (lastItem.page) + 1
                    }
                }
            }

            val movies = api.getMovies(
                title = title,
                page = loadKey
            )
            val response = movies.toMovieEntities(loadKey)

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