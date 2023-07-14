package com.nocountry.movie_no_country.feature_home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nocountry.movie_no_country.MainActivity
import com.nocountry.movie_no_country.databinding.FragmentHomeBinding
import com.nocountry.movie_no_country.feature_home.model.Cartelera
import com.nocountry.movie_no_country.feature_home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var binding : FragmentHomeBinding? = null
    lateinit var adapter: HomeAdapter
    private val viewmodel: HomeViewModel by viewModel()
    var list : ArrayList<Cartelera> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        recyclerView()
        (activity as MainActivity).showBottomNav(true)
        return binding?.root
    }
    fun recyclerView(){
        binding?.apply {
            list = viewmodel.listCart.value!!
            adapter = HomeAdapter(list)
            viewmodel.getCarteleras(requireActivity(),adapter)
            rvHome.layoutManager = GridLayoutManager(context,2)
            rvHome.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}