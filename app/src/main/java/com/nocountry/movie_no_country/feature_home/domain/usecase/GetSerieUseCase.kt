package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.core.DEFAULT_LANGUAGE_CODE
import com.nocountry.movie_no_country.core.DEFAULT_REGION
import com.nocountry.movie_no_country.feature_home.domain.SerieRepository

class GetSerieUseCase(
    private val serieRepository: SerieRepository
) {

    suspend operator fun invoke(
        language: String = DEFAULT_LANGUAGE_CODE,
        region: String = DEFAULT_REGION
    ) = serieRepository.getSeries(
        language, region
    )
}