package com.nocountry.movie_no_country.feature_favorite.viewmodel

import androidx.lifecycle.ViewModel
import com.nocountry.movie_no_country.feature_home.db.FavoriteMoviesDAO
import com.nocountry.movie_no_country.feature_home.domain.model.FavoriteMovieEntity

class FavoritesViewModel(
    private val dao: FavoriteMoviesDAO
):ViewModel() {

    suspend fun getFavorites():List<FavoriteMovieEntity>{
        return dao.allFavoriteMovies()
    }
}