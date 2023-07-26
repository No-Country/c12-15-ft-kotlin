package com.nocountry.movie_no_country.feature_home.data.network.detail

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("runtime")
    val runtime: Int,
)
