package com.nocountry.movie_no_country.feature_home.data

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieDto
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieService
import com.nocountry.movie_no_country.feature_home.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImp(
    private val movieService: MovieService,
) : MovieRepository {
    override suspend fun getPopularMovies(
        language: String,
        region: String
    ): NetworkResult<ApiResult<List<MovieDto>>> {
        return withContext(Dispatchers.IO) {
            movieService.getMovies(language, region)
        }
    }

    override suspend fun discover(
        language: String,
        with_genres: Int
    ): NetworkResult<ApiResult<List<MovieDto>>> {
        return withContext(Dispatchers.IO) {
            movieService.discover(language, with_genres)
        }
    }
}