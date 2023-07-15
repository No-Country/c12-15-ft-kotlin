package com.nocountry.movie_no_country.feature_home.di

import com.nocountry.movie_no_country.feature_home.data.MovieRepositoryImp
import com.nocountry.movie_no_country.feature_home.data.network.genre.GenreApi
import com.nocountry.movie_no_country.feature_home.data.network.genre.GenreService
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieApi
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieService
import com.nocountry.movie_no_country.feature_home.domain.MovieRepository
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildPosterUrlUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetPopularMoviesUseCase
import com.nocountry.movie_no_country.feature_home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    factory {
        MovieService(
            get<Retrofit>().create(
                MovieApi::class.java
            )
        )
    }

    factory {
        GenreService(
            get<Retrofit>().create(
                GenreApi::class.java
            )
        )
    }

    factoryOf(::MovieRepositoryImp) { bind<MovieRepository>() }

    viewModelOf(::HomeViewModel)

    factoryOf(::GetPopularMoviesUseCase)
    factoryOf(::BuildPosterUrlUseCase)
}