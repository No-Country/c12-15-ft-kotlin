package com.nocountry.movie_no_country.di.modules

import com.nocountry.movie_no_country.core.AuthInterceptor
import com.nocountry.movie_no_country.core.BASE_URL
import com.nocountry.movie_no_country.core.COMMENTS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(Qualifier.BASE_URL)))
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BASIC
            )
        ).addInterceptor(AuthInterceptor())
            .build()
    }

    single(named(Qualifier.BASE_URL)) {
        BASE_URL
    }
    single(named(Qualifier.COMMENTS)) {
        COMMENTS
    }
    single(named(Api.COMMENTS)){
        Retrofit.Builder()
            .baseUrl(get<String>(named(Qualifier.COMMENTS)))
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
enum class Qualifier {
    BASE_URL,
    COMMENTS
}
enum class Api{
    COMMENTS
}