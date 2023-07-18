package com.nocountry.movie_no_country.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val appModule = module {
    single {
        FirebaseAuth.getInstance()
    }
    single {
        FirebaseFirestore.getInstance()
    }
}