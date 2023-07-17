package com.nocountry.movie_no_country.feature_home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nocountry.movie_no_country.databinding.CarteleraItemBinding
import com.nocountry.movie_no_country.feature_home.domain.model.Movie

class HomeAdapter(var items: List<Movie>, private val onClickMovie: OnMovieClicked) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    interface OnMovieClicked{
        fun OnclickMovieListener(detail: Movie, position: Int)

    }

    inner class ViewHolder(private val binding: CarteleraItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(detail: Movie) {
            binding.apply {
                detail.posterUrl.let { img ->
                    Glide.with(root.context)
                        .load(img)
                        .into(imageViewHome)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        val binding = CarteleraItemBinding.inflate(LayoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detail = items[position]
        holder.bind(detail)
        holder.itemView.setOnClickListener {
            onClickMovie.OnclickMovieListener(items[position], position)
        }
    }

}