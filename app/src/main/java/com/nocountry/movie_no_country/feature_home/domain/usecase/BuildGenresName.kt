package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.feature_home.data.network.genre.GenreItem

class BuildGenresName {

    operator fun invoke(ids: List<Int>, genres: List<GenreItem>) = ids.toGenreName(genres)

    private fun List<Int?>.toGenreName(list: List<GenreItem>): String {
        var genresName = ""
        for (id in this) {
            val genre = list.find {
                it.id == id
            }?.name
            genresName += "${genre ?: ""}, "
        }
        return genresName.removeSuffix(", ")
    }
}