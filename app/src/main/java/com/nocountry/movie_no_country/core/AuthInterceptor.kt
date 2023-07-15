package com.nocountry.movie_no_country.core

import com.nocountry.movie_no_country.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}").build()
        return chain.proceed(request)
    }
}