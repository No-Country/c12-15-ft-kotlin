package com.nocountry.movie_no_country.feature_home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nocountry.movie_no_country.MainActivity
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentHomeDetailBinding
import com.nocountry.movie_no_country.feature_home.domain.model.Movie

class HomeDetail : Fragment() {
    private var binding : FragmentHomeDetailBinding? = null
    private val args : HomeDetailArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeDetailBinding.inflate(inflater,container,false)
        bindDetail(args.detail)
        binding?.imageViewFavorites?.setOnClickListener {
            it.background = context?.getDrawable(R.drawable.ic_favorites_selected)
        }
        //navigationToHome()
        return binding?.root
    }


    private fun bindDetail(detail: Movie){
        binding?.apply {
            (activity as MainActivity).setSupportActionBar(toolbar)
            (activity as MainActivity).supportActionBar?.title = detail.title
            textViewOverView.text = detail.overview
            textViewReleaseData.text = detail.releaseDate
            textViewTitle2.text = detail.originalTitle
            textviewGener.text = detail.genres
            textViewRating.text = detail.voteAverage.toString()
            detail.backdropUrl.let { img ->
                Glide.with(root.context)
                    .load(img)
                    .into(imageViewDetail)
            }
        }
    }
//    private fun navigationToHome(){
//        binding?.toolbar?.setOnClickListener {
//            findNavController().navigate(R.id.action_homeDetail_to_homeFragment)
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}