package com.nocountry.movie_no_country.feature_home.domain.usecase

class BuildDurationUseCase {

    operator fun invoke(runtime: Int): String {
        val hours = runtime / 60
        val minutesRemainder = runtime % 60
        return String.format("%d h %02d min", hours, minutesRemainder)
    }
}