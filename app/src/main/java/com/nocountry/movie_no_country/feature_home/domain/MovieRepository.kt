package com.nocountry.movie_no_country.feature_home.domain

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieDto

interface MovieRepository {

    suspend fun getPopularMovies(
        language: String, region: String
    ): NetworkResult<ApiResult<List<MovieDto>>>

    suspend fun discover(
        language: String, with_genres: Int
    ): NetworkResult<ApiResult<List<MovieDto>>>
}