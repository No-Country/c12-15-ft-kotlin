package com.nocountry.movie_no_country.feature_home.data

import com.nocountry.movie_no_country.core.data.model.ApiResult
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.serie.SerieDto
import com.nocountry.movie_no_country.feature_home.data.network.serie.SerieService
import com.nocountry.movie_no_country.feature_home.domain.SerieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SerieRepositoryImp(
    private val serieService: SerieService
) : SerieRepository {

    override suspend fun getSeries(
        language: String,
        region: String
    ): NetworkResult<ApiResult<List<SerieDto>>> {
        return withContext(Dispatchers.IO) {
            serieService.getSeries(language, region)
        }
    }

    override suspend fun discoverSerie(
        language: String,
        with_genres: Int
    ): NetworkResult<ApiResult<List<SerieDto>>> {
        return withContext(Dispatchers.IO) {
            serieService.discoverSerie(language, with_genres)
        }
    }
}