package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.feature_home.domain.MovieRepository

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke() = movieRepository.getPopularMovies()
}