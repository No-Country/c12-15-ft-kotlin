package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.core.BASE_URL_IMAGE

class BuildPosterUrlUseCase {
    operator fun invoke(posterPath: String) = "$BASE_URL_IMAGE${posterPath}"
}