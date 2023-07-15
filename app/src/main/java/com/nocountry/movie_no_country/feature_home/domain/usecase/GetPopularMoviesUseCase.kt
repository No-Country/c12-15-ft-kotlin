package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.core.DEFAULT_LANGUAGE_CODE
import com.nocountry.movie_no_country.core.DEFAULT_REGION
import com.nocountry.movie_no_country.feature_home.domain.MovieRepository

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        language: String = DEFAULT_LANGUAGE_CODE,
        region: String = DEFAULT_REGION
    ) = movieRepository.getPopularMovies(
        language, region
    )
}