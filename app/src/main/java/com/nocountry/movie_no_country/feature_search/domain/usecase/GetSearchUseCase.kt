package com.nocountry.movie_no_country.feature_search.domain.usecase

import com.nocountry.movie_no_country.core.DEFAULT_LANGUAGE_CODE
import com.nocountry.movie_no_country.feature_search.domain.SearchRepository

class GetSearchUseCase(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(
        language: String = DEFAULT_LANGUAGE_CODE,
        query :String
    ) = searchRepository.getSearch(language,query)
}