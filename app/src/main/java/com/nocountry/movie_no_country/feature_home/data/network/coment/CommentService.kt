package com.nocountry.movie_no_country.feature_home.data.network.coment

import com.nocountry.movie_no_country.core.data.model.handleApi

class CommentService(private val commentApi: CommentApi) {
    suspend fun getMovies() = handleApi {
        commentApi.getComments()
    }
}