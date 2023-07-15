package com.nocountry.movie_no_country.feature_home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.nocountry.movie_no_country.MainActivity
import com.nocountry.movie_no_country.databinding.FragmentHomeBinding
import com.nocountry.movie_no_country.feature_home.data.network.MovieDto
import com.nocountry.movie_no_country.feature_home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).showBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setCollectors()

        return binding?.root
    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listCart.collectLatest {
                    recyclerView(it)
                }
            }
        }
    }

    private fun recyclerView(list: List<MovieDto>) {
        binding?.apply {
            adapter = HomeAdapter(list)
            viewModel.getPopularMovies()
            rvHome.layoutManager = GridLayoutManager(context, 2)
            rvHome.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}