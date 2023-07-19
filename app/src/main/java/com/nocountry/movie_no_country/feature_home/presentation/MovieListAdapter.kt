package com.nocountry.movie_no_country.feature_home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.core.LIMIT
import com.nocountry.movie_no_country.databinding.MovieItemBinding
import com.nocountry.movie_no_country.databinding.ViewMoreBinding
import com.nocountry.movie_no_country.feature_home.domain.model.Movie

class MovieListAdapter(
    private val movies: List<Movie>,
    private val fragment: Fragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.movie_item -> MovieListViewHolder.MovieViewHolder(
                MovieItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            ) { itemClicked(fragment, movies[it]) }

            else -> {
                MovieListViewHolder.ViewMoreViewHolder(
                    ViewMoreBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    fragment
                )
            }
        }
    }

    private fun itemClicked(fragment: Fragment, movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToHomeDetail(movie)
        fragment.findNavController().navigate(action)
    }

    override fun getItemCount() = if (movies.size > HOME_LIMIT_PER_SECTION)
        HOME_LIMIT_PER_SECTION + 1 else movies.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieListViewHolder.MovieViewHolder -> holder.bindMovie(movies[position])
            is MovieListViewHolder.ViewMoreViewHolder -> holder
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position == LIMIT) {
            true -> R.layout.view_more
            false -> R.layout.movie_item
        }
    }

    sealed class MovieListViewHolder(
        binding: ViewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        class MovieViewHolder(
            private val binding: MovieItemBinding,
            private val onItemClicked: (Int) -> Unit
        ) : MovieListViewHolder(binding) {

            init {
                itemView.setOnClickListener {
                    onItemClicked(adapterPosition)
                }
            }

            fun bindMovie(movie: Movie) {
                Glide.with(itemView).load(movie.posterUrl).into(binding.moviePoster)
            }
        }

        class ViewMoreViewHolder(
            binding: ViewMoreBinding,
            fragment: Fragment
        ) : MovieListViewHolder(binding) {
            init {
                itemView.setOnClickListener {
                    // TODO navigate to category movies
                }
            }
        }
    }
}

const val HOME_LIMIT_PER_SECTION = LIMIT