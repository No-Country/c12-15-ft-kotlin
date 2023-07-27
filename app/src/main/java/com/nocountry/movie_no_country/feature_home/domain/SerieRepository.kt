package com.nocountry.movie_no_country.feature_home.domain

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.serie.SerieDto

interface SerieRepository {
    suspend fun getSeries(
        language: String, region: String
    ): NetworkResult<ApiResult<List<SerieDto>>>

    suspend fun discoverSerie(
        language: String, with_genres: Int
    ): NetworkResult<ApiResult<List<SerieDto>>>
}