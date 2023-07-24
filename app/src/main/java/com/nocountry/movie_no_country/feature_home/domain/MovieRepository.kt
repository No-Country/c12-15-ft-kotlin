package com.nocountry.movie_no_country.feature_home.domain

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieDto
import com.nocountry.movie_no_country.feature_home.domain.model.DataOrException
import com.nocountry.movie_no_country.feature_home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(
        language: String,
        region: String
    ): Flow<NetworkResult<ApiResult<List<MovieDto>>>>

    suspend fun discover(
        language: String,
        with_genres: Int
    ): Flow<NetworkResult<ApiResult<List<MovieDto>>>>
}