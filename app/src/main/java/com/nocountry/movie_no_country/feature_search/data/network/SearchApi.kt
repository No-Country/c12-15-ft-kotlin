package com.nocountry.movie_no_country.feature_search.data.network

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/movie")
    suspend fun getSearch(
        @Query("language") language: String,
        @Query("query") query : String
    ): Response<ApiResult<List<MovieDto>>>
}