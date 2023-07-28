package com.nocountry.movie_no_country.di

import android.app.Application
import com.nocountry.movie_no_country.di.modules.appModule
import com.nocountry.movie_no_country.di.modules.retrofitModule
import com.nocountry.movie_no_country.feature_home.di.homeModule
import com.nocountry.movie_no_country.feature_search.di.searchModule
import org.koin.core.context.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                appModule,
                retrofitModule,
                homeModule,
                searchModule
            )
        }

    }
    companion object{
        private var instace : MovieApp? = null
         fun aplicationContext()= instace!!.applicationContext
    }
    init {
        instace = this
    }

}