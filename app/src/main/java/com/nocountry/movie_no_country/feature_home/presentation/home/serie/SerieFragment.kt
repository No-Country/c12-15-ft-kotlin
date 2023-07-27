package com.nocountry.movie_no_country.feature_home.presentation.home.serie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nocountry.movie_no_country.databinding.FragmentSerieBinding
import com.nocountry.movie_no_country.feature_home.presentation.home.HomeAdapter
import com.nocountry.movie_no_country.feature_home.presentation.home.HomeViewModel
import com.nocountry.movie_no_country.feature_home.presentation.home.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SerieFragment : Fragment() {
    private var binding: FragmentSerieBinding? = null
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSerieBinding.inflate(inflater, container, false)
        setAdapters()
        return binding?.root
    }

    private fun setAdapters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateSerie.observe(viewLifecycleOwner) {
                when (it) {
                    is UiState.Error -> binding?.includeLoading?.progressBar?.isVisible = false
                    UiState.Loading -> binding?.includeLoading?.progressBar?.isVisible = true
                    is UiState.Success -> {
                        binding?.includeLoading?.progressBar?.isVisible = false
                        adapter = HomeAdapter(it.items, this@SerieFragment)
                        binding?.includeSerie?.rvHome?.adapter = adapter
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}