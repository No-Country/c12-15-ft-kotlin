package com.nocountry.movie_no_country.feature_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nocountry.movie_no_country.databinding.FragmentSearchBinding
import com.nocountry.movie_no_country.feature_search.presentation.SearchAdapter
import com.nocountry.movie_no_country.feature_search.presentation.SearchViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        setupObserver()
        setListener()
        return binding?.root
    }

    private fun setupObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.search.observe(viewLifecycleOwner){
                binding?.includeListMovie?.apply {
                    if (it != null) {
                        searchAdapter = SearchAdapter(it)
                    }
                    rvSearch.adapter = searchAdapter
                }
            }
        }
    }
    private fun setListener(){
        binding?.searchView?.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?)= false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.getSearch(it) }
                binding?.textviewGone?.isVisible = newText?.isBlank() == true
                return true
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}