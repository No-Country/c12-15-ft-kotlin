package com.nocountry.movie_no_country.feature_home.data

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieDto
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieService
import com.nocountry.movie_no_country.feature_home.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImp(
    private val movieService: MovieService,
) : MovieRepository {
    override suspend fun getPopularMovies(
        language: String,
        region: String
    ): Flow<NetworkResult<ApiResult<List<MovieDto>>>> {
        return flowOf(
            movieService.getMovies(language, region)
        ).catch {
            NetworkResult.Exception<Throwable>(it)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun discover(
        language: String,
        with_genres: Int
    ): Flow<NetworkResult<ApiResult<List<MovieDto>>>> {
        return flowOf(
            movieService.discover(language, with_genres)
        ).catch {
            NetworkResult.Exception<Throwable>(it)
        }.flowOn(Dispatchers.IO)
    }
}