package com.nocountry.movie_no_country.feature_home.data.network.cast

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CastApi {
    @GET("movie/{movieId}")
    suspend fun getMovieCast(
        @Path("movieId") movieId: String,
    ): Response<CastDto>
}