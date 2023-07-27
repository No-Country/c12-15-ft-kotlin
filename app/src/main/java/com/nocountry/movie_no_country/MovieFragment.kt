package com.nocountry.movie_no_country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nocountry.movie_no_country.databinding.FragmentMovieBinding
import com.nocountry.movie_no_country.feature_home.presentation.HomeAdapter
import com.nocountry.movie_no_country.feature_home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private var binding: FragmentMovieBinding? = null
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        setAdapters()
        return binding?.root
    }

    private fun setAdapters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.observe(viewLifecycleOwner) {
                adapter = HomeAdapter(it, this@MovieFragment)
                binding?.rvHome?.adapter = adapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}