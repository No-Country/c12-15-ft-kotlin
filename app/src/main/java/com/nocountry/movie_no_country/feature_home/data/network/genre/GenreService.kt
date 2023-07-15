package com.nocountry.movie_no_country.feature_home.data.network.genre

import com.nocountry.movie_no_country.core.data.model.handleApi

class GenreService(
    private val genreApi: GenreApi
) {

    suspend fun getMovieGenres(language: String) = handleApi {
        genreApi.getMovieGenres(language)
    }

    suspend fun getTvGenres(language: String) = handleApi {
        genreApi.getTvGenres(language)
    }
}