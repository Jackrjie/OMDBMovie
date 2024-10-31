package com.jackrjie.omdbmovie.domain.repository

import androidx.paging.Pager
import com.jackrjie.omdbmovie.data.local.MovieEntity

interface MovieRepository {

    fun getMovies(title: String): Pager<Int, MovieEntity>
}