package com.nocountry.movie_no_country.feature_home.data.network.movie

import com.nocountry.movie_no_country.core.data.model.handleApi

class MovieService(
    private val movieApi: MovieApi
) {

    suspend fun getMovies(
        language: String,
        region: String
    ) = handleApi {
        movieApi.getPopularMovies(language, region)
    }
}