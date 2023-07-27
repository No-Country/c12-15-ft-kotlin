package com.nocountry.movie_no_country.feature_home.data.network.serie

import com.nocountry.movie_no_country.core.data.model.handleApi

class SerieService(
    private val serieApi: SerieApi
) {

    suspend fun getSeries(
        language: String,
        region: String
    ) = handleApi {
        serieApi.getPopularMovies(language, region)
    }

    suspend fun discoverSerie(
        language: String,
        with_genres: Int
    ) = handleApi {
        serieApi.discoverMovie(language, with_genres)
    }
}