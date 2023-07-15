package com.nocountry.movie_no_country.feature_home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.domain.model.Movie
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildPosterUrlUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val buildPosterUrlUseCase: BuildPosterUrlUseCase
) : ViewModel() {
    private val _listCart = MutableStateFlow<List<Movie>>(emptyList())
    var listCart: StateFlow<List<Movie>> = _listCart

    fun getPopularMovies() {

        viewModelScope.launch {
            val res = getPopularMoviesUseCase()
            res.collectLatest { result ->
                when (result) {
                    is NetworkResult.Error -> Log.i("error mov", result.message ?: "")
                    is NetworkResult.Exception -> Log.i("exc mov", result.e.message.toString())
                    is NetworkResult.Success -> _listCart.value = result.data.results.map {
                        Movie(
                            id = it.id,
                            posterUrl = buildPosterUrlUseCase(it.posterPath)
                        )
                    }
                }
            }
        }
    }
}