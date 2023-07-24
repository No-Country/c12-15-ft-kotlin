package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.feature_home.domain.CastRepository

class GetCastUseCase(
    private val castRepository: CastRepository
) {

    suspend operator fun invoke(
        id: String
    ) = castRepository.getCast(id)
}