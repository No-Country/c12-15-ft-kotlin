package com.nocountry.movie_no_country.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.nocountry.movie_no_country.feature_home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        FirebaseAuth.getInstance()
    }

    viewModelOf(::HomeViewModel)
}