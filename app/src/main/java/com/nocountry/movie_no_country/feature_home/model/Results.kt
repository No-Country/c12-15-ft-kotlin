package com.nocountry.movie_no_country.feature_home.model

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("results")
    val cartelera : ArrayList<Cartelera>
)
