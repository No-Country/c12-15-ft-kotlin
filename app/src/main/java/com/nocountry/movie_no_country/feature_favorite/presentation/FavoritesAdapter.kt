package com.nocountry.movie_no_country.feature_favorite.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nocountry.movie_no_country.databinding.MovieItemBinding

class FavoritesAdapter(private val list : List<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolderFav(binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root){
        private val posterImage = binding.moviePoster

        fun bind(imageUrl:String){
            Glide.with(itemView.context).load(imageUrl).into(posterImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFav(MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderFav).bind(list[position])
    }
}

