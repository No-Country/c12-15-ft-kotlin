package com.nocountry.movie_no_country.feature_home.data.network

import com.nocountry.movie_no_country.feature_home.model.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("now_playing")
    suspend fun getCartelera(@Query("api_key") apiKey: String): Response<Results>
}