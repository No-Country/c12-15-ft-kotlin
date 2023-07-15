package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.core.DEFAULT_LANGUAGE_CODE
import com.nocountry.movie_no_country.feature_home.domain.GenreRepository

class GetMovieGenresUseCase(
    private val genreRepository: GenreRepository
) {

    suspend operator fun invoke(
        language: String = DEFAULT_LANGUAGE_CODE,
    ) = genreRepository.getMovieGenres(language)
}