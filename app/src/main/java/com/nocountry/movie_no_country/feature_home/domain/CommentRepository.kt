package com.nocountry.movie_no_country.feature_home.domain


import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.domain.model.Results


interface CommentRepository {
    suspend fun getComments(): NetworkResult<Results>//Flow<NetworkResult<ApiResult<List<MovieDto>>>>
}