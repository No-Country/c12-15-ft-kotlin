package com.nocountry.movie_no_country.feature_home.data.network.detail

import com.nocountry.movie_no_country.core.data.model.handleApi

class DetailsService(private val detail: DetailApi) {
    suspend fun getDetails(language : String,movieId:String)= handleApi{
        detail.getDetails(language,movieId)
    }

}