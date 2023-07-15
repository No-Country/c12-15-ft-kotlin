package com.nocountry.movie_no_country.feature_authentication.signup.domain

data class NewUser(
    val email : String,
    val password : String,
    val name : String,
    val lastName : String
)
