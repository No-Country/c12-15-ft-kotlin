package com.nocountry.movie_no_country.feature_home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.domain.model.Movie
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildBackDropUrlUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildPosterUrlUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetMovieGenresUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetPopularMoviesUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetYearUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val buildPosterUrlUseCase: BuildPosterUrlUseCase,
    private val movieGenresUseCase: GetMovieGenresUseCase,
    private val buildBackDropUrlUseCase: BuildBackDropUrlUseCase,
    private val getYearUseCase: GetYearUseCase
) : ViewModel() {
    private val _listCart = MutableStateFlow<List<Movie>>(emptyList())
    var listCart: StateFlow<List<Movie>> = _listCart

    private val _genres = MutableStateFlow<List<String>>(emptyList())
    var genres: StateFlow<List<String>> = _genres

    init {
        getPopularMovies()
        getGenres()
    }

    private fun getPopularMovies() {

        viewModelScope.launch {
            val res = getPopularMoviesUseCase()
            res.collectLatest { result ->
                when (result) {
                    is NetworkResult.Error -> Log.i("error mov", result.message ?: "")
                    is NetworkResult.Exception -> Log.i("exc mov", result.e.message.toString())
                    is NetworkResult.Success -> _listCart.value = result.data.results.map {
                        Movie(
                            id = it.id,
                            posterUrl = buildPosterUrlUseCase(it.posterPath),
                            title = it.title,
                            overview = it.overview,
                            releaseDate = getYearUseCase(it.releaseDate),
                            genreIds = it.genreIds,
                            voteAverage = it.voteAverage,
                            originalTitle = it.originalTitle,
                            backdropUrl = buildBackDropUrlUseCase(it.backdropPath),
                        )
                    }
                }
            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch {

            when (val genres = movieGenresUseCase()) {
                is NetworkResult.Error -> Log.i("error mov", genres.message ?: "")
                is NetworkResult.Exception -> Log.i("exc mov", genres.e.message.toString())
                is NetworkResult.Success -> _genres.value = genres.data.genres.map {
                    it.name
                }
            }
        }
    }
}