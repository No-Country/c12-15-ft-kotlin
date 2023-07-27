package com.nocountry.movie_no_country.feature_home.data.network.serie

import com.nocountry.movie_no_country.core.data.model.ApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SerieApi {

    @GET("tv/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("region") region: String
    ): Response<ApiResult<List<SerieDto>>>

    @GET("discover/tv")
    suspend fun discoverMovie(
        @Query("language") language: String,
        @Query("with_genres") region: Int
    ): Response<ApiResult<List<SerieDto>>>
}