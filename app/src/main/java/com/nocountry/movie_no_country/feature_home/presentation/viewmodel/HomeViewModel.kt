package com.nocountry.movie_no_country.feature_home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.movie_no_country.core.data.model.NetworkResult
import com.nocountry.movie_no_country.feature_home.data.network.genre.GenreItem
import com.nocountry.movie_no_country.feature_home.domain.model.Movie
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildBackDropUrlUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildGenresName
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildPosterUrlUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.DiscoverMoviesUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetMovieGenresUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetPopularMoviesUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetYearUseCase
import com.nocountry.movie_no_country.feature_home.presentation.model.HomeRecyclerItem
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val buildPosterUrlUseCase: BuildPosterUrlUseCase,
    private val movieGenresUseCase: GetMovieGenresUseCase,
    private val buildBackDropUrlUseCase: BuildBackDropUrlUseCase,
    private val buildGenresName: BuildGenresName,
    private val getYearUseCase: GetYearUseCase,
    private val discoverMoviesUseCase: DiscoverMoviesUseCase
) : ViewModel() {

    private val _data = MutableLiveData<List<HomeRecyclerItem>>()
    val data: LiveData<List<HomeRecyclerItem>> = _data

    private val homeData = mutableListOf<HomeRecyclerItem>()

    private val genresList = MutableLiveData<List<GenreItem>>(emptyList())

    init {
        getPopularMovies()
        getGenres()
    }

    private fun setData(
        genreId: Int = 0,
        genreName: String = "Top 10 de películas en Argentina",
        movies: List<Movie>
    ) {
        homeData.add(
            HomeRecyclerItem.Section(genreId, genreName)
        )
        homeData.add(
            HomeRecyclerItem.Movies(
                movies
            )
        )
        _data.postValue(homeData)
    }

    private fun getPopularMovies() {

        viewModelScope.launch {
            when (val result = getPopularMoviesUseCase()) {
                is NetworkResult.Error -> Log.i("error mov", result.message ?: "")
                is NetworkResult.Exception -> Log.i("exc mov", result.e.message.toString())
                is NetworkResult.Success -> {
                    setData(movies = result.data.results.map {
                        Movie(
                            id = it.id,
                            posterUrl = buildPosterUrlUseCase(it.posterPath),
                            title = it.title,
                            overview = it.overview,
                            releaseDate = getYearUseCase(it.releaseDate),
                            genres = buildGenresName(it.genreIds, genresList.value?: emptyList()),
                            voteAverage = it.voteAverage,
                            originalTitle = it.originalTitle,
                            backdropUrl = buildBackDropUrlUseCase(it.backdropPath),
                            originalLanguage = it.originalLanguage,
                            popularity = it.popularity
                        )
                    })
                }
            }
        }
    }


    private suspend fun discover(with_genres: Int, genreName: String) {
        when (val result = discoverMoviesUseCase(with_genres = with_genres)) {
            is NetworkResult.Error -> Log.i("error mov", result.message ?: "")
            is NetworkResult.Exception -> Log.i("exc mov", result.e.message.toString())
            is NetworkResult.Success -> {
                val genres = genresList.value ?: emptyList()
                setData(with_genres, genreName, result.data.results.map {
                    Movie(
                        id = it.id,
                        posterUrl = buildPosterUrlUseCase(it.posterPath),
                        title = it.title,
                        overview = it.overview,
                        releaseDate = getYearUseCase(it.releaseDate),
                        genres = buildGenresName(it.genreIds, genres),
                        voteAverage = it.voteAverage,
                        originalTitle = it.originalTitle,
                        backdropUrl = buildBackDropUrlUseCase(it.backdropPath),
                        originalLanguage = it.originalLanguage,
                        popularity = it.popularity
                    )
                })
            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            when (val genres = movieGenresUseCase()) {
                is NetworkResult.Error -> Log.i("error mov", genres.message ?: "")
                is NetworkResult.Exception -> Log.i("exc mov", genres.e.message.toString())
                is NetworkResult.Success -> {
                    genresList.value = genres.data.genres
                    for (genre in genres.data.genres) {
                        discover(genre.id, genre.name)
                    }
                }
            }
        }
    }
}

