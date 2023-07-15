package com.nocountry.movie_no_country.feature_home.data

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.MovieDto
import com.nocountry.movie_no_country.feature_home.data.network.MovieService
import com.nocountry.movie_no_country.feature_home.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImp(
    private val movieService: MovieService
) : MovieRepository {
    override suspend fun getPopularMovies(): Flow<NetworkResult<ApiResult<List<MovieDto>>>> {
        return flowOf(
            movieService.getMovies("es", "AR")
        ).catch {
            NetworkResult.Exception<Throwable>(it)
        }.flowOn(Dispatchers.IO)
    }
}