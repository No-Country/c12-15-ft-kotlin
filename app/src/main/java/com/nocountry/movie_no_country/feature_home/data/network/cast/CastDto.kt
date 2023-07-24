package com.nocountry.movie_no_country.feature_home.data.network.cast

import com.google.gson.annotations.SerializedName

data class CastDto(
    @SerializedName("original_name")
    val originalName: String
)