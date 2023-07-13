package com.nocountry.movie_no_country.di

import android.app.Application
import com.nocountry.movie_no_country.di.modules.appModule
import org.koin.core.context.startKoin

class MovieApp : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(appModule)
        }
    }
}