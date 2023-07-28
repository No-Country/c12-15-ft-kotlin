package com.nocountry.movie_no_country.feature_search.data.network

import com.nocountry.movie_no_country.core.data.model.handleApi

class SearchService(private val searchApi: SearchApi) {
    suspend fun getSearch(
        language: String,
        query : String
    ) = handleApi {
        searchApi.getSearch(language,query)
    }
}