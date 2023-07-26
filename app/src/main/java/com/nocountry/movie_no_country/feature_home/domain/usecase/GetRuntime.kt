package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.core.DEFAULT_LANGUAGE_CODE
import com.nocountry.movie_no_country.feature_home.domain.DetailRepository

class GetRuntime(private val detailsRepository: DetailRepository) {
    suspend operator fun invoke(movieId: String, language: String = DEFAULT_LANGUAGE_CODE) =
        detailsRepository.getDetails(movieId,language)
}