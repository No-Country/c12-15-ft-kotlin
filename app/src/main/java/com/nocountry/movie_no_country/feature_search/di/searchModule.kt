package com.nocountry.movie_no_country.feature_search.di

import com.nocountry.movie_no_country.feature_search.data.network.SearchApi
import com.nocountry.movie_no_country.feature_search.data.network.SearchService
import com.nocountry.movie_no_country.feature_search.data.SearchRepositoryImp
import com.nocountry.movie_no_country.feature_search.domain.SearchRepository
import com.nocountry.movie_no_country.feature_search.domain.usecase.GetSearchUseCase
import com.nocountry.movie_no_country.feature_search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

val searchModule = module {
    single {
        SearchService(
            get<Retrofit>().create(
                SearchApi::class.java
            )
        )
    }

    factoryOf(::SearchRepositoryImp){bind<SearchRepository>()}
    factoryOf(::GetSearchUseCase)
    viewModelOf(::SearchViewModel)
}