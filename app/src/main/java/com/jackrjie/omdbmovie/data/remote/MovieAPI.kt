package com.jackrjie.omdbmovie.data.remote

import com.jackrjie.omdbmovie.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("/")
    suspend fun getMovies(
        @Query("apikey") apiKey: String = BuildConfig.API_KEY,
        @Query("s") title :String,
        @Query("page") page: Int,
    ) : MovieDto
}