package com.nocountry.movie_no_country

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nocountry.movie_no_country.feature_home.presentation.home.movie.MovieFragment
import com.nocountry.movie_no_country.feature_home.presentation.home.serie.SerieFragment

class HomeFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) = when (position) {
        0 -> MovieFragment()
        else -> SerieFragment()
    }
}