package com.nocountry.movie_no_country.feature_home.data.network.cast

import com.nocountry.movie_no_country.core.data.model.handleApi

class CastService(
    private val castApi: CastApi
) {

    suspend fun getMovieCast(id: String) = handleApi {
        castApi.getMovieCast(id)
    }
}