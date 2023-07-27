package com.nocountry.movie_no_country.feature_home.presentation.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.nocountry.movie_no_country.MainActivity
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentHomeDetailBinding
import com.nocountry.movie_no_country.feature_home.domain.model.Movie
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeDetail : Fragment() {
    private var binding: FragmentHomeDetailBinding? = null
    private val args: HomeDetailArgs by navArgs()
    private val viewModel: HomeDetailViewModel by viewModel()
    private lateinit var  myadapter : DetailAdapter
    private var favoriteClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeDetailBinding.inflate(inflater, container, false)
        bindDetail(args.detail)
        viewModel.getRuntimes(args.detail.id.toString())
        viewModel.runtime.observe(viewLifecycleOwner) {
            binding?.textViewDurationTime?.text = it
        }
        //movieSave = args.detail
        //onClickFavorite()
        //navigationToHome()
        isFavorite(args.detail.id)
        onClickFavorite()
        setupObserver()
        return binding?.root
    }

    private fun bindDetail(detail: Movie) {
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

    private fun onClickFavorite() {
        binding?.imageViewFavorites?.setOnClickListener {
            if (favoriteClicked) removeFavorites(args.detail.id) else addFavorites(args.detail)
            setIcon(!favoriteClicked)
        }
    }

    private fun setIcon(click: Boolean) {
        binding?.imageViewFavorites?.background = AppCompatResources.getDrawable(
            this.requireContext(), if (click) {
                favoriteClicked = true
                R.drawable.ic_favorites_selected
            } else {
                favoriteClicked = false
                R.drawable.ic_favorites_detail
            }
        )
    }

    private fun addFavorites(movie: Movie) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.saveToAddToFavotire(movie)
            snackBar("¡Agregado a tus favoritos!")
        }
    }

    private fun removeFavorites(id: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.deleteFavorite(id)
            snackBar("¡Eliminado de tus favoritos!")
        }
    }

    private fun isFavorite(id: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            val isFavorite = viewModel.isFavorites(id).isNotEmpty()
            favoriteClicked = if (isFavorite) {
                setIcon(true)
                true
            } else {
                false
            }
        }
    }
    private fun snackBar(message: String){
        binding?.frameLayout9?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }
    private fun setupObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.comments.observe(viewLifecycleOwner, Observer {
                binding?.rvDetail?.apply {
                    if (it != null) {
                        myadapter = DetailAdapter(it.results)
                    }
                    adapter = myadapter
                }
            })
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}