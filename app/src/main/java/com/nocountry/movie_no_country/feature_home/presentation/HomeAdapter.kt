package com.nocountry.movie_no_country.feature_home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nocountry.movie_no_country.core.BASE_URL_IMAGE
import com.nocountry.movie_no_country.databinding.CarteleraItemBinding
import com.nocountry.movie_no_country.feature_home.model.Cartelera

class HomeAdapter(var items: ArrayList<Cartelera>):RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CarteleraItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(detail: Cartelera){
            binding.apply {
                detail.poster_path.let { img->
                    Glide.with(root.context)
                        .load("${BASE_URL_IMAGE}${img}")
                        .into(imageViewHome)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        val binding = CarteleraItemBinding.inflate(LayoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detail = items[position]
        holder.bind(detail)
    }

}