package com.nocountry.movie_no_country.feature_home.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nocountry.movie_no_country.feature_home.domain.FavoriteMovieEntity

@Dao
interface FavoriteMoviesDAO {
    @Query("SELECT * FROM favorite_movies")
    suspend fun allFavoriteMovies(): List<FavoriteMovieEntity>

    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    suspend fun isThereAMovie(id: Int): List<FavoriteMovieEntity>

    @Query("DELETE FROM favorite_movies WHERE id  = :id")
    suspend fun removeFavorite(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(movieDTO: FavoriteMovieEntity)
}