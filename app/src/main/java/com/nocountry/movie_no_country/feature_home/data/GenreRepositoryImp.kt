package com.nocountry.movie_no_country.feature_home.data

import com.nocountry.movie_no_country.feature_home.data.network.genre.GenreService
import com.nocountry.movie_no_country.feature_home.domain.GenreRepository

class GenreRepositoryImp(
    private val genreService: GenreService
) : GenreRepository {
    override suspend fun getMovieGenres(
        language: String
    ) = genreService.getMovieGenres(language)

    override suspend fun getTvGenres(
        language: String
    ) = genreService.getTvGenres(language)
}