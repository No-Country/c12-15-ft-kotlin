package com.nocountry.movie_no_country.feature_home.presentation.home.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nocountry.movie_no_country.databinding.ReviewItemBinding
import com.nocountry.movie_no_country.feature_home.domain.model.Coments

class DetailAdapter(private val list: List<Coments>) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Coments) {
            binding.apply {
                textViewComments.text = comment.description
                textViewNameComment.text = comment.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ReviewItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comments = list[position]
        holder.bind(comments)
    }

    override fun getItemCount() = list.size

}