package com.nocountry.movie_no_country.feature_home.data.network

import com.nocountry.movie_no_country.core.data.model.ApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("region") region: String
    ): Response<ApiResult<MovieDto>>
}