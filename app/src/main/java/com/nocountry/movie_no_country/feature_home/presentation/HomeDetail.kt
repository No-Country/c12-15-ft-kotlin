package com.nocountry.movie_no_country.feature_home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentHomeDetailBinding

class HomeDetail : Fragment() {
    private var binding : FragmentHomeDetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeDetailBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}