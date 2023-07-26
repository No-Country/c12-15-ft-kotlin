package com.nocountry.movie_no_country.feature_home.data.network.detail

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailApi {
    @GET("movie/{movieId}")
    suspend fun getDetails(
        @Path("movieId") movieId:String,
        @Query("language") language: String
    ): Response<MovieDetails>
}