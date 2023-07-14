package com.nocountry.movie_no_country.feature_home.service

import com.nocountry.movie_no_country.feature_home.core.Constantes
import com.nocountry.movie_no_country.feature_home.core.RetrofitService
import com.nocountry.movie_no_country.feature_home.model.Results
import com.nocountry.movie_no_country.feature_home.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DataProcess {

    private val retrofit = RetrofitService.getRetrofit()

    suspend fun getCartelera(): Response<Results> {
        return withContext(Dispatchers.IO){
            retrofit.create(ApiService::class.java).getCartelera(Constantes.API_KEY)
        }
    }
}