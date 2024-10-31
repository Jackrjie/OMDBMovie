package com.jackrjie.omdbmovie.domain.usecase

import com.jackrjie.omdbmovie.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repo: MovieRepository
) {

    operator fun invoke() {

    }
}