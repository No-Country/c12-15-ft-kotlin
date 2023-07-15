package com.nocountry.movie_no_country.feature_home.data.network.genre

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApi {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("language") language: String,
    ): Response<GenreDto>

    @GET("genre/tv/list")
    suspend fun getTvGenres(
        @Query("language") language: String,
    ): Response<GenreDto>
}