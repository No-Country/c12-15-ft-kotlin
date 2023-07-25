package com.nocountry.movie_no_country.feature_home.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
//    val votesAverage: Float,
//    val title: String,
//    val posterImageUrl: String?,
//    val backdropImageUrl: String?,
//    val overview: String,
//    val releaseDate: String,
//    val language: String,
//    val popularity: Float,
//    val movieId: Long,
)