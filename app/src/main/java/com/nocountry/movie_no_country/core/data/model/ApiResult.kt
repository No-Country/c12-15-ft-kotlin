package com.nocountry.movie_no_country.core.data.model

data class ApiResult<T>(
    val page: Int,
    val results: T,
    val total_pages: Int,
    val total_results: Int
)