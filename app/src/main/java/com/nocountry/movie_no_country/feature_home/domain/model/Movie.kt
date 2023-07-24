package com.nocountry.movie_no_country.feature_home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Movie(
    val backdropUrl: String?,
    val genres: String = "",
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String = "",
    val overview: String,
    val popularity: Double,
    val posterUrl: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean = false,
    val voteAverage: Double
): Parcelable