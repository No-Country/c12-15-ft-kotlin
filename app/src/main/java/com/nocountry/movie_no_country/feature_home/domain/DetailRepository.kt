package com.nocountry.movie_no_country.feature_home.domain

import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.detail.MovieDetails

interface DetailRepository {

    suspend fun getDetails(language:String, movieId:String): NetworkResult<MovieDetails>
}