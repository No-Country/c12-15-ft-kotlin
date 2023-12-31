package com.nocountry.movie_no_country.feature_home.di

import com.nocountry.movie_no_country.di.modules.Api
import com.nocountry.movie_no_country.feature_favorite.viewmodel.FavoritesViewModel
import com.nocountry.movie_no_country.feature_home.data.CastRepositoryImp
import com.nocountry.movie_no_country.feature_home.data.CommentRepositoryImp
import com.nocountry.movie_no_country.feature_home.data.DetailsRepositoryImp
import com.nocountry.movie_no_country.feature_home.data.GenreRepositoryImp
import com.nocountry.movie_no_country.feature_home.data.MovieRepositoryImp
import com.nocountry.movie_no_country.feature_home.data.SerieRepositoryImp
import com.nocountry.movie_no_country.feature_home.data.network.cast.CastApi
import com.nocountry.movie_no_country.feature_home.data.network.cast.CastService
import com.nocountry.movie_no_country.feature_home.data.network.coment.CommentApi
import com.nocountry.movie_no_country.feature_home.data.network.coment.CommentService
import com.nocountry.movie_no_country.feature_home.data.network.detail.DetailApi
import com.nocountry.movie_no_country.feature_home.data.network.detail.DetailsService
import com.nocountry.movie_no_country.feature_home.data.network.genre.GenreApi
import com.nocountry.movie_no_country.feature_home.data.network.genre.GenreService
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieApi
import com.nocountry.movie_no_country.feature_home.data.network.movie.MovieService
import com.nocountry.movie_no_country.feature_home.data.network.serie.SerieApi
import com.nocountry.movie_no_country.feature_home.data.network.serie.SerieService
import com.nocountry.movie_no_country.feature_home.domain.CastRepository
import com.nocountry.movie_no_country.feature_home.domain.CommentRepository
import com.nocountry.movie_no_country.feature_home.domain.DetailRepository
import com.nocountry.movie_no_country.feature_home.domain.GenreRepository
import com.nocountry.movie_no_country.feature_home.domain.MovieRepository
import com.nocountry.movie_no_country.feature_home.domain.SerieRepository
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildBackDropUrlUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildDurationUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildGenresName
import com.nocountry.movie_no_country.feature_home.domain.usecase.BuildPosterUrlUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.DiscoverMoviesUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.DiscoverSerieUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetAirDateUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetCastUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetCommentsUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetMovieGenresUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetPopularMoviesUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetRuntime
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetSerieUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetTvGenresUseCase
import com.nocountry.movie_no_country.feature_home.domain.usecase.GetYearUseCase
import com.nocountry.movie_no_country.feature_home.presentation.home.HomeViewModel
import com.nocountry.movie_no_country.feature_home.presentation.home.detail.HomeDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
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

    factory {
        CastService(
            get<Retrofit>().create(
                CastApi::class.java
            )
        )
    }

    factory {
        DetailsService(
            get<Retrofit>().create(
                DetailApi::class.java
            )
        )
    }
    factory {
        CommentService(
            get<Retrofit>(named(Api.COMMENTS)).create(
                CommentApi::class.java
            )
        )
    }

    factory {
        SerieService(
            get<Retrofit>().create(
                SerieApi::class.java
            )
        )
    }

    factoryOf(::MovieRepositoryImp) { bind<MovieRepository>() }

    factoryOf(::GenreRepositoryImp) { bind<GenreRepository>() }
    factoryOf(::CastRepositoryImp) { bind<CastRepository>() }
    factoryOf(::DetailsRepositoryImp) { bind<DetailRepository>() }
    factoryOf(::CommentRepositoryImp) { bind<CommentRepository>() }

    factoryOf(::SerieRepositoryImp) { bind<SerieRepository>() }

    viewModelOf(::HomeViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::HomeDetailViewModel)

    factoryOf(::GetPopularMoviesUseCase)
    factoryOf(::BuildPosterUrlUseCase)
    factoryOf(::GetTvGenresUseCase)
    factoryOf(::GetMovieGenresUseCase)
    factoryOf(::BuildBackDropUrlUseCase)
    factoryOf(::GetYearUseCase)
    factoryOf(::DiscoverMoviesUseCase)
    factoryOf(::BuildGenresName)
    factoryOf(::GetCastUseCase)
    factoryOf(::GetRuntime)
    factoryOf(::BuildDurationUseCase)
    factoryOf(::GetCommentsUseCase)
    factoryOf(::GetSerieUseCase)
    factoryOf(::DiscoverSerieUseCase)
    factoryOf(::GetAirDateUseCase)
}