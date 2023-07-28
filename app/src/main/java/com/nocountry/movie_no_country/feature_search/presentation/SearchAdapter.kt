package com.nocountry.movie_no_country.feature_search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nocountry.movie_no_country.databinding.MovieItemBinding

class SearchAdapter(private val list : List<String>):RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(private val binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(search: String){
            binding.apply {
                Glide.with(binding.root)
                    .load(search)
                    .into(moviePoster)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val search = list[position]
        holder.bind(search)
    }
}