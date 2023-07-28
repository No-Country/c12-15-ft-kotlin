package com.nocountry.movie_no_country.feature_home.presentation.home

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
import com.nocountry.movie_no_country.feature_home.domain.usecase.DiscoverSerieUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetAirDateUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetMovieGenresUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetPopularMoviesUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetSerieUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetTvGenresUseCase
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
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,
    private val getSerieUseCase: GetSerieUseCase,
    private val discoverSerieUseCase: DiscoverSerieUseCase,
    private val getTvGenresUseCase: GetTvGenresUseCase,
    private val getAirDateUseCase: GetAirDateUseCase,
) : ViewModel() {

    private val _stateMovie = MutableLiveData<UiState>(UiState.Loading)
    val stateMovie: LiveData<UiState> = _stateMovie

    private val _stateSerie = MutableLiveData<UiState>(UiState.Loading)
    val stateSerie: LiveData<UiState> = _stateSerie

//    private val _data = MutableLiveData<List<HomeRecyclerItem>>()
//    val data: LiveData<List<HomeRecyclerItem>> = _data
//
//    private val _series = MutableLiveData<List<HomeRecyclerItem>>()
//    val series: LiveData<List<HomeRecyclerItem>> = _series

    private val homeData = mutableListOf<HomeRecyclerItem>()
    private val serieData = mutableListOf<HomeRecyclerItem>()

    private val movieGenresList = MutableLiveData<List<GenreItem>>(emptyList())
    private val tvGenreList = MutableLiveData<List<GenreItem>>(emptyList())

    init {
        getPopularMovies()
        getMovieGenres()
        getSeries()
        getTvGenres()
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
    }

    private fun setSerie(
        genreId: Int = 0,
        genreName: String = "Top 10 de series en Argentina",
        movies: List<Movie>
    ) {
        serieData.add(
            HomeRecyclerItem.Section(genreId, genreName)
        )
        serieData.add(
            HomeRecyclerItem.Movies(
                movies
            )
        )
    }

    fun updateMovie(movies: List<HomeRecyclerItem>) {
        _stateMovie.value = UiState.Success(movies)
//        _data.value = homeData
    }

    fun updateSerie(series: List<HomeRecyclerItem>) {
//        _series.value = serieData
        _stateSerie.value = UiState.Success(series)
    }

    private fun getPopularMovies() {

        viewModelScope.launch {
            when (val result = getPopularMoviesUseCase()) {
                is NetworkResult.Error -> UiState.Error(result.message.toString())
                is NetworkResult.Exception -> UiState.Error(result.e.message.toString())
                is NetworkResult.Success -> {
                    setData(movies = result.data.results.map {
                        Movie(
                            id = it.id,
                            posterUrl = buildPosterUrlUseCase(it.posterPath),
                            title = it.title,
                            overview = it.overview,
                            releaseDate = getYearUseCase(it.releaseDate),
                            genres = buildGenresName(
                                it.genreIds,
                                movieGenresList.value ?: emptyList()
                            ),
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

    private fun getSeries() {

        viewModelScope.launch {
            when (val result = getSerieUseCase()) {
                is NetworkResult.Error -> UiState.Error(result.message.toString())
                is NetworkResult.Exception -> UiState.Error(result.e.message.toString())
                is NetworkResult.Success -> {
                    setSerie(movies = result.data.results.map {
                        Movie(
                            id = it.id,
                            posterUrl = buildPosterUrlUseCase(it.posterPath),
                            title = it.originaTitle,
                            overview = it.overview,
                            releaseDate = getAirDateUseCase(it.releaseDate),
                            genres = buildGenresName(it.genreIds, tvGenreList.value ?: emptyList()),
                            voteAverage = it.voteAverage,
                            originalTitle = it.originaTitle,
                            backdropUrl = buildBackDropUrlUseCase(it.backdropPath),
                            originalLanguage = it.originalLanguage,
                            popularity = it.popularity
                        )
                    })
                }
            }
        }
    }

    private suspend fun discoverSeries(with_genres: Int, genreName: String) {
        when (val result = discoverSerieUseCase(with_genres = with_genres)) {
            is NetworkResult.Error -> UiState.Error(result.message.toString())
            is NetworkResult.Exception -> UiState.Error(result.e.message.toString())
            is NetworkResult.Success -> {
                val genres = tvGenreList.value ?: emptyList()
                setSerie(with_genres, genreName, result.data.results.map {
                    Movie(
                        id = it.id,
                        posterUrl = buildPosterUrlUseCase(it.backdropPath),
                        title = it.name,
                        overview = it.overview,
                        releaseDate = it.releaseDate,
                        genres = buildGenresName(it.genreIds, genres),
                        voteAverage = it.voteAverage,
                        originalTitle = it.originaTitle,
                        backdropUrl = buildBackDropUrlUseCase(it.backdropPath),
                        originalLanguage = it.originalLanguage,
                        popularity = it.popularity
                    )
                })
            }
        }
    }


    private suspend fun discover(with_genres: Int, genreName: String) {
        when (val result = discoverMoviesUseCase(with_genres = with_genres)) {
            is NetworkResult.Error -> UiState.Error(result.message.toString())
            is NetworkResult.Exception -> UiState.Error(result.e.message.toString())
            is NetworkResult.Success -> {
                val genres = movieGenresList.value ?: emptyList()
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

    private fun getMovieGenres() {
        viewModelScope.launch {
            when (val genres = movieGenresUseCase()) {
                is NetworkResult.Error -> UiState.Error(genres.message.toString())
                is NetworkResult.Exception -> UiState.Error(genres.e.message.toString())
                is NetworkResult.Success -> {
                    movieGenresList.value = genres.data.genres
                    for (genre in genres.data.genres) {
                        discover(genre.id, genre.name)
                    }
                    updateMovie(homeData.toList())
                }
            }
        }
    }

    private fun getTvGenres() {
        viewModelScope.launch {
            when (val genres = getTvGenresUseCase()) {
                is NetworkResult.Error -> UiState.Error(genres.message.toString())
                is NetworkResult.Exception -> UiState.Error(genres.e.message.toString())
                is NetworkResult.Success -> {
                    tvGenreList.value = genres.data.genres
                    for (genre in genres.data.genres) {
                        discoverSeries(genre.id, genre.name)
                    }
                    updateSerie(serieData.toList())
                }
            }
        }
    }
}

sealed class UiState {
    data class Success(val items: List<HomeRecyclerItem>) : UiState()
    object Loading : UiState()
    data class Error(val msg: String) : UiState()
}

