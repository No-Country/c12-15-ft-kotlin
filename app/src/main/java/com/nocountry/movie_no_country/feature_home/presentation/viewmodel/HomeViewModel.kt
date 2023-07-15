package com.nocountry.movie_no_country.feature_home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.MovieDto
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    val listCart = MutableLiveData<ArrayList<MovieDto>>()

    init {
        listCart.value = ArrayList()
    }

    fun getCarteleras() {
        CoroutineScope(Dispatchers.IO).launch {
            val res = getPopularMoviesUseCase()

            res.collectLatest {
                when (it) {
                    is NetworkResult.Error -> Log.i("error mov", it.message ?: "")
                    is NetworkResult.Exception -> Log.i("exc mov", it.e.message.toString())
                    is NetworkResult.Success -> listCart.value?.addAll(listOf(it.data.results))
                }
            }
        }
    }
}