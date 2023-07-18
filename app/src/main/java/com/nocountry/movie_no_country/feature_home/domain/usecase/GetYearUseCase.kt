package com.nocountry.movie_no_country.feature_home.domain.usecase

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetYearUseCase{
    operator fun invoke(date : String )= "${LocalDate.parse(date, DateTimeFormatter.ISO_DATE).year}"
}