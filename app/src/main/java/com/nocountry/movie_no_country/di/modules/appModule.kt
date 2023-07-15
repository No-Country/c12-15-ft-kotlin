package com.nocountry.movie_no_country.di.modules

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val appModule = module {
    single {
        FirebaseAuth.getInstance()
    }
}