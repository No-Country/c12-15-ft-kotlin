package com.nocountry.movie_no_country.feature_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nocountry.movie_no_country.MainActivity
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentLanguageSettingBinding

class LanguageSettingFragment : Fragment() {
    private var binding : FragmentLanguageSettingBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLanguageSettingBinding.inflate(inflater,container,false)
        binding.apply {
            (activity as MainActivity).supportActionBar?.title = "REACH"
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}