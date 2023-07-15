package com.nocountry.movie_no_country.feature_home.data.network.genre

data class GenreDto(
    val genres: GenreItem
)

data class GenreItem(
    val id: Int,
    val name: String
)