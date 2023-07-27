package com.nocountry.movie_no_country.feature_home.data.network.serie

import com.google.gson.annotations.SerializedName

data class SerieDto(
    @SerializedName("first_air_date")
    val releaseDate: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originaTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("video")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
)