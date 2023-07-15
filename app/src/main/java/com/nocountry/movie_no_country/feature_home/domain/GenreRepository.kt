package com.nocountry.movie_no_country.feature_home.domain

import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.genre.GenreDto

interface GenreRepository {

    suspend fun getMovieGenres(
        language: String,
    ): NetworkResult<GenreDto>

    suspend fun getTvGenres(
        language: String,
    ): NetworkResult<GenreDto>
}