package com.nocountry.movie_no_country.feature_home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.MovieDto
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _listCart = MutableStateFlow<List<MovieDto>>(emptyList())
    var listCart: StateFlow<List<MovieDto>> = _listCart

    fun getPopularMovies() {

        viewModelScope.launch {
            val res = getPopularMoviesUseCase()
            res.collectLatest {
                when (it) {
                    is NetworkResult.Error -> Log.i("error mov", it.message ?: "")
                    is NetworkResult.Exception -> Log.i("exc mov", it.e.message.toString())
                    is NetworkResult.Success -> _listCart.value = it.data.results
                }
            }
        }
    }
}