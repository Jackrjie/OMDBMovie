package com.jackrjie.omdbmovie.data.remote

import com.jackrjie.omdbmovie.domain.model.Movie
import com.squareup.moshi.Json

data class MovieDto(
    @Json(name = "Response") val response: String,
    @Json(name = "Search") val search: List<Search>,
    val totalResults: String
)

data class Search(
    @Json(name = "Poster") val poster: String,
    @Json(name = "Title") val title: String,
    @Json(name = "Type") val type: String,
    @Json(name = "Year") val year: String,
    @Json(name = "imdbID") val imdbID: String
)

fun MovieDto.toMovies() :List<Movie> {
    return search.map { search ->
        Movie(
            poster = search.poster,
            title = search.title,
            year = search.year,
            imdbID = search.imdbID
        )
    }
}