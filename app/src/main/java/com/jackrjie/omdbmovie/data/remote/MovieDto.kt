package com.jackrjie.omdbmovie.data.remote

import com.google.gson.annotations.SerializedName
import com.jackrjie.omdbmovie.data.local.MovieEntity

data class MovieDto(
    @SerializedName("Response") val response: String,
    @SerializedName("Search") val search: List<Search>,
    val totalResults: String
)

data class Search(
    @SerializedName("Poster") val poster: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String
)

fun MovieDto.toMovieEntities(page: Int) :List<MovieEntity> {
    return search.map { search ->
        MovieEntity(
            poster = search.poster,
            title = search.title,
            year = search.year,
            imdbID = search.imdbID,
            page = page
        )
    }
}