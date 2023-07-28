package com.nocountry.movie_no_country.feature_search.data

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieDto
import com.nocountry.movie_no_country.feature_search.data.network.SearchService
import com.nocountry.movie_no_country.feature_search.domain.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepositoryImp(private val searchService: SearchService):SearchRepository {

    override suspend fun getSearch(language: String, query:String): NetworkResult<ApiResult<List<MovieDto>>> {
        return withContext(Dispatchers.IO) {
            searchService.getSearch(language,query)
        }
    }
}