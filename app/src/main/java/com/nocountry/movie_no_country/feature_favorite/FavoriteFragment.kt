package com.nocountry.movie_no_country.feature_favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.nocountry.movie_no_country.databinding.FragmentFavoriteBinding
import com.nocountry.movie_no_country.feature_favorite.presentation.FavoritesAdapter
import com.nocountry.movie_no_country.feature_favorite.viewmodel.FavoritesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private var binding : FragmentFavoriteBinding? = null
    private lateinit var adapterFav : FavoritesAdapter
    private val viewModel : FavoritesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        setAdapter()
        return binding?.root
    }
    private fun setAdapter(){
        viewLifecycleOwner.lifecycleScope.launch {
            adapterFav = FavoritesAdapter(viewModel.getFavorites().map {
                it.posterImageUrl?: ""
            })
            binding?.includeFavorite?.rvFavoritesItem?.adapter = adapterFav
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}