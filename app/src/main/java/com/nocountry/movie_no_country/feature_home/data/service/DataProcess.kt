package com.nocountry.movie_no_country.feature_home.data.service

import com.nocountry.movie_no_country.BuildConfig
import com.nocountry.movie_no_country.feature_home.data.network.ApiService
import com.nocountry.movie_no_country.feature_home.model.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class DataProcess (private val retrofit: Retrofit) {

    suspend fun getCartelera(): Response<Results> {
        return withContext(Dispatchers.IO){
            retrofit.create(ApiService::class.java).getCartelera(BuildConfig.PRIVATE_KEY)
        }
    }
}