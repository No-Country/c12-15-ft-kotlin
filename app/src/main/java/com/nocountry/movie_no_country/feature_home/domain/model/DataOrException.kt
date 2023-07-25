package com.nocountry.movie_no_country.feature_home.domain.model

data class DataOrException<T, E : Exception>(
    val data: T? = null,
    val exception: E? = null
)