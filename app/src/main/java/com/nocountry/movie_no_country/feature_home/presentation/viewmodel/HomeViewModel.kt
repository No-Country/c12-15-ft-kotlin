package com.nocountry.movie_no_country.feature_home.presentation.viewmodel

import android.util.Log
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val buildPosterUrlUseCase: BuildPosterUrlUseCase,
    private val movieGenresUseCase: GetMovieGenresUseCase,
    private val buildBackDropUrlUseCase: BuildBackDropUrlUseCase,
    private val buildGenresName: BuildGenresName,
    private val getYearUseCase: GetYearUseCase,
    private val discoverMoviesUseCase: DiscoverMoviesUseCase
) : ViewModel() {

    private val _data = MutableStateFlow<MutableList<HomeRecyclerItem>>(mutableListOf())
    var data: StateFlow<MutableList<HomeRecyclerItem>> = _data

    private val homeData = mutableListOf<HomeRecyclerItem>()

    private val genresCurrent = MutableStateFlow<List<GenreItem>>(emptyList())

    init {
        getGenres()
        getPopularMovies()

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
        _data.value = homeData
    }

    private fun getPopularMovies() {

        viewModelScope.launch {
            val res = getPopularMoviesUseCase()
            res.collectLatest { result ->
                when (result) {
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
                                genres = buildGenresName(it.genreIds, genresCurrent.value),
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
    }

    private suspend fun discover(with_genres: Int, genreName: String) {
        discoverMoviesUseCase(with_genres = with_genres)
            .collectLatest { result ->
                when (result) {
                    is NetworkResult.Error -> Log.i("error mov", result.message ?: "")
                    is NetworkResult.Exception -> Log.i("exc mov", result.e.message.toString())
                    is NetworkResult.Success -> {
                        setData(
                            with_genres,
                            genreName,
                            result.data.results.map {
                                Movie(
                                    id = it.id,
                                    posterUrl = buildPosterUrlUseCase(it.posterPath),
                                    title = it.title,
                                    overview = it.overview,
                                    releaseDate = getYearUseCase(it.releaseDate),
                                    genres = buildGenresName(it.genreIds, genresCurrent.value),
                                    voteAverage = it.voteAverage,
                                    originalTitle = it.originalTitle,
                                    backdropUrl = buildBackDropUrlUseCase(it.backdropPath),
                                    originalLanguage = it.originalLanguage,
                                    popularity = it.popularity
                                )
                            }
                        )
                    }
                }

            }
    }


    private fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {

            when (val genres = movieGenresUseCase()) {
                is NetworkResult.Error<*> -> Log.i("error mov", genres.message ?: "")
                is NetworkResult.Exception<*> -> Log.i("exc mov", genres.e.message.toString())
                is NetworkResult.Success -> {
                    genresCurrent.value = genres.data.genres
                    for (genre in genresCurrent.value) {
                        withContext(Dispatchers.IO) {
                            runBlocking {
                                discover(genre.id, genre.name)
                            }
                        }
                    }
                }
            }
        }
    }

}