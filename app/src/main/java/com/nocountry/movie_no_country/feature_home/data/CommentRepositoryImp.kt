package com.nocountry.movie_no_country.feature_home.data

import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.coment.CommentService
import com.nocountry.movie_no_country.feature_home.domain.CommentRepository
import com.nocountry.movie_no_country.feature_home.domain.model.Results

class CommentRepositoryImp(private val commentService: CommentService):CommentRepository {
    override suspend fun getComments(): NetworkResult<Results> {
       return commentService.getMovies()
    }
}