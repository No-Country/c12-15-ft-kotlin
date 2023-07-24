package com.nocountry.movie_no_country.feature_home.data

import com.nocountry.movie_no_country.feature_home.data.network.cast.CastService
import com.nocountry.movie_no_country.feature_home.domain.CastRepository

class CastRepositoryImp(
    private val castService: CastService
) : CastRepository {
    override suspend fun getCast(id: String) = castService.getMovieCast(id)
}