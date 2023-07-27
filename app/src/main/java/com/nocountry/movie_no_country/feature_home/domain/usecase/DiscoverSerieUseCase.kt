package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.core.DEFAULT_LANGUAGE_CODE
import com.nocountry.movie_no_country.feature_home.domain.SerieRepository

class DiscoverSerieUseCase(
    private val serieRepository: SerieRepository
) {

    suspend operator fun invoke(
        language: String = DEFAULT_LANGUAGE_CODE,
        with_genres: Int
    ) = serieRepository.discoverSerie(language, with_genres)
}