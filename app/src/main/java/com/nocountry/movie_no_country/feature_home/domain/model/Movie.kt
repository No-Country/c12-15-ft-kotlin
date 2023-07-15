package com.nocountry.movie_no_country.feature_home.domain.model

data class Movie(
    val backdropUrl: String = "",
    val genreIds: List<Int> = emptyList(),
    val id: Int,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterUrl: String,
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)