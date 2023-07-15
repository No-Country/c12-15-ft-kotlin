package com.nocountry.movie_no_country.feature_home.di

import com.nocountry.movie_no_country.feature_home.data.network.MovieApi
import com.nocountry.movie_no_country.feature_home.data.network.MovieService
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
}