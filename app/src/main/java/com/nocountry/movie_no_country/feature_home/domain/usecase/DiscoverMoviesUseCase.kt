package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.core.DEFAULT_LANGUAGE_CODE
import com.nocountry.movie_no_country.feature_home.domain.MovieRepository

class DiscoverMoviesUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(
        language: String = DEFAULT_LANGUAGE_CODE,
        with_genres: Int
    ) = movieRepository.discover(language, with_genres)
}