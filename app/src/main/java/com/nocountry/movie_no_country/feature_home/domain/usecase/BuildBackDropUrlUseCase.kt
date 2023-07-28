package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.core.BASE_URL_IMAGE

class BuildBackDropUrlUseCase {

    operator fun invoke(backdropPath: String?) =
        backdropPath?.let { "${BASE_URL_IMAGE}${backdropPath}" }
}