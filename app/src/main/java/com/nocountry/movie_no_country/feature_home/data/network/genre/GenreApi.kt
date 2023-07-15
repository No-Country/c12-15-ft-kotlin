package com.nocountry.movie_no_country.feature_home.data.network.genre

import retrofit2.Response
import retrofit2.http.GET

interface GenreApi {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(
    ): Response<GenreDto>

    @GET("genre/tv/list")
    suspend fun getTvGenres(
    ): Response<GenreDto>
}