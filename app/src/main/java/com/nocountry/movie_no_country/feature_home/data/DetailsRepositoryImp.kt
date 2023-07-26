package com.nocountry.movie_no_country.feature_home.data

import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.detail.DetailsService
import com.nocountry.movie_no_country.feature_home.data.network.detail.MovieDetails
import com.nocountry.movie_no_country.feature_home.domain.DetailRepository

class DetailsRepositoryImp(private val detailsService: DetailsService):DetailRepository {
    override suspend fun getDetails(
        language:String,
        movieId: String
    ): NetworkResult<MovieDetails> = detailsService.getDetails(language,movieId)
}