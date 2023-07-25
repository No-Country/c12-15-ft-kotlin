package com.nocountry.movie_no_country.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nocountry.movie_no_country.di.MovieApp
import com.nocountry.movie_no_country.feature_home.db.DataBaseFactory
import org.koin.dsl.module

val appModule = module {
    single {
        FirebaseAuth.getInstance()
    }
    single {
        Firebase.firestore
    }
    single {
        DataBaseFactory.getDatabase(MovieApp.aplicationContext()).favoriteMoviesDAO()
    }
}