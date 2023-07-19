package com.nocountry.movie_no_country.feature_home.presentation.model

import com.nocountry.movie_no_country.feature_home.domain.model.Movie

sealed class HomeRecyclerItem {
    class Section(
        val id: Int,
        val title: String
    ) : HomeRecyclerItem()

    class Movies(
        val list: List<Movie>
    ) : HomeRecyclerItem()
}