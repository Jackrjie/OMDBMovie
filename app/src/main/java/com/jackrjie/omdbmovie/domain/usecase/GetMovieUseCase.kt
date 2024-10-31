package com.jackrjie.omdbmovie.domain.usecase

import androidx.paging.Pager
import com.jackrjie.omdbmovie.data.local.MovieEntity
import com.jackrjie.omdbmovie.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repo: MovieRepository
) {

    operator fun invoke(title: String): Pager<Int, MovieEntity> {
        return repo.getMovies(title)
    }
}