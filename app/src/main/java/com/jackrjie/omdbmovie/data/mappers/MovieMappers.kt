package com.jackrjie.omdbmovie.data.mappers

import com.jackrjie.omdbmovie.data.local.MovieEntity
import com.jackrjie.omdbmovie.domain.model.Movie

fun MovieEntity.toMovie(): Movie {
    return Movie(
        imdbID = imdbID,
        poster = poster,
        title = title,
        year = year
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        imdbID = imdbID,
        poster = poster,
        title = title,
        year = year
    )
}