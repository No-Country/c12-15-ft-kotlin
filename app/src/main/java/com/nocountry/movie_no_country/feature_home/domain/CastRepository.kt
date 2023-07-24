package com.nocountry.movie_no_country.feature_home.domain

import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.cast.CastDto

interface CastRepository {

    suspend fun getCast(id: String): NetworkResult<CastDto>
}