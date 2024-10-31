package com.jackrjie.omdbmovie.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val imdbID: String,
    val poster: String,
    val title: String,
    val year: String,
    val page: Int
)