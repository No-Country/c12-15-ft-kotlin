package com.nocountry.movie_no_country.feature_home.data.network.genre

import com.nocountry.movie_no_country.core.data.model.handleApi

class GenreService(
    private val genreApi: GenreApi
) {

    suspend fun getMovieGenres() = handleApi {
        genreApi.getMovieGenres()
    }

    suspend fun getTvGenres() = handleApi {
        genreApi.getTvGenres()
    }
}