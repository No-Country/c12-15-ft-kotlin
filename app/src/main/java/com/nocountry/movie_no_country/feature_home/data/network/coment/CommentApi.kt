package com.nocountry.movie_no_country.feature_home.data.network.coment

import com.nocountry.movie_no_country.feature_home.domain.model.Results
import retrofit2.Response
import retrofit2.http.GET

interface CommentApi {
    @GET("coments")
    suspend fun getComments(): Response<Results>
}