package com.nocountry.movie_no_country.feature_home.data.network

class MovieService(
    private val movieApi: MovieApi
) {

    suspend fun getMovies(
        language: String,
        region: String
    ) = movieApi.getPopularMovies(language, region)
}