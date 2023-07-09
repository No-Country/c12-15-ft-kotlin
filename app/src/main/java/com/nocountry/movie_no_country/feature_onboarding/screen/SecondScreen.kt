package com.nocountry.movie_no_country.feature_onboarding.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentFirstScreenBinding
import com.nocountry.movie_no_country.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {
    private var binding : FragmentSecondScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondScreenBinding.inflate(inflater,container,false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.ViewPager)
        binding?.buttonScreen2?.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}