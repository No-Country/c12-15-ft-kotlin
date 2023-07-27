package com.nocountry.movie_no_country.feature_home.presentation.home.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.db.FavoriteMoviesDAO
import com.nocountry.movie_no_country.feature_home.domain.model.FavoriteMovieEntity
import com.nocountry.movie_no_country.feature_home.domain.model.Movie
import com.nocountry.movie_no_country.feature_home.domain.model.Results
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildDurationUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetCommentsUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetRuntime
import kotlinx.coroutines.launch

class HomeDetailViewModel(
    private val dao: FavoriteMoviesDAO,
    private val getRuntime: GetRuntime,
    private val duration: BuildDurationUseCase,
    private val getComments : GetCommentsUseCase
    ): ViewModel() {
    private val _runtime = MutableLiveData<String>()
    var runtime : LiveData<String> = _runtime

    private val _comments = MutableLiveData<Results?>()
    var comments : LiveData<Results?> = _comments
    init {
        getComment()
    }
    fun getRuntimes(movieId:String){
        viewModelScope.launch {
            val result = getRuntime(movieId)
            when(result){
                is NetworkResult.Error -> Log.d("HomeDetailViewModel", "error")
                is NetworkResult.Exception -> Log.d("HomeDetailViewModel", "exception")
                is NetworkResult.Success -> _runtime.value = duration(result.data.runtime)
            }
        }
    }
    fun getComment(){
        viewModelScope.launch {
            val result = getComments()
            when(result){
                is NetworkResult.Error -> Log.d("HomeDetailViewModel", "error Comments")
                is NetworkResult.Exception -> Log.d("HomeDetailViewModel", "exception Comments")
                is NetworkResult.Success -> _comments.value = result.data
            }
        }
    }
    suspend fun saveToAddToFavotire(movie: Movie) {
        dao.saveFavorite(
            FavoriteMovieEntity(
                id = movie.id,
                title = movie.title,
                posterImageUrl = movie.posterUrl
            )
        )
    }

    suspend fun isFavorites(id: Int) = dao.isThereAMovie(id)
    suspend fun deleteFavorite(id: Int) {
        dao.removeFavorite(id)
    }
}