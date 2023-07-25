package com.nocountry.movie_no_country.feature_home.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nocountry.movie_no_country.feature_home.domain.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 2, exportSchema = false)
abstract class DataBaseFactory : RoomDatabase() {
    abstract fun favoriteMoviesDAO(): FavoriteMoviesDAO

    companion object {
        private var INSTANCE: DataBaseFactory? = null
        fun getDatabase(context: Context): DataBaseFactory {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        DataBaseFactory::class.java,
                        "data-base"
                    ).build()
                INSTANCE= instance
                return instance
            }
        }
    }
}