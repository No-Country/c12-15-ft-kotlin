package com.nocountry.movie_no_country.feature_home.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.HeaderHomeSectionBinding
import com.nocountry.movie_no_country.databinding.MovieListBinding
import com.nocountry.movie_no_country.feature_home.domain.model.Movie
import com.nocountry.movie_no_country.feature_home.presentation.model.HomeRecyclerItem

class HomeAdapter(
    private val items: List<HomeRecyclerItem>,
    private val fragment: Fragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.header_home_section -> HomeViewHolder.HeaderViewHolder(
                HeaderHomeSectionBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            R.layout.movie_list -> HomeViewHolder.MovieListViewHolder(
                MovieListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                fragment
            )

            else -> throw IllegalAccessException("Type not defined.")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeRecyclerItem.Section -> R.layout.header_home_section
            is HomeRecyclerItem.Movies -> R.layout.movie_list
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder.HeaderViewHolder -> holder.bind((items[position] as HomeRecyclerItem.Section).title)
            is HomeViewHolder.MovieListViewHolder -> {
                holder.bindMovies(
                    (items[position] as HomeRecyclerItem.Movies).list
                )
            }
        }
    }

    sealed class HomeViewHolder(
        binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        class HeaderViewHolder(
            private val binding: HeaderHomeSectionBinding
        ) : HomeViewHolder(binding) {
            fun bind(name: String) {
                binding.section.text = name
            }
        }

        class MovieListViewHolder(
            private val binding: MovieListBinding,
            private val fragment: Fragment
        ) : HomeViewHolder(binding) {
            fun bindMovies(
                movies: List<Movie>,
            ) {
                val adapter = MovieListAdapter(
                    movies,
                    fragment = fragment
                )
                binding.movies.adapter = adapter
            }
        }
    }
}