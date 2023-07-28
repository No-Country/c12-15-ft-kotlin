package com.nocountry.movie_no_country.feature_search.domain

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieDto

interface SearchRepository {
    suspend fun getSearch(
        language: String,
        query:String
    ): NetworkResult<ApiResult<List<MovieDto>>>
}