package com.nocountry.movie_no_country.feature_home.presentation

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.nocountry.movie_no_country.MainActivity
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentHomeDetailBinding
import com.nocountry.movie_no_country.feature_home.domain.model.Movie
import com.nocountry.movie_no_country.feature_home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeDetail : Fragment() {
    private var binding: FragmentHomeDetailBinding? = null
    private val args: HomeDetailArgs by navArgs()
    private val db = get<FirebaseFirestore>()
    private val viewModel: HomeViewModel by viewModel()

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
        //movieSave = args.detail
        //onClickFavorite()
        //navigationToHome()
        isFavorite(args.detail.id)

        onClickFavorite()

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

    private fun saveIdToDb(id: Int) {
        val movie = hashMapOf(
            "id" to id,
        )
        db.collection("favorites")
            .document("12121212@gmail.com")
            .update(movie as Map<String, Any>)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
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
        }
    }

    private fun removeFavorites(id: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.deleteFavorite(id)
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}