package com.nocountry.movie_no_country.feature_onboarding.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentOnboardingFirstScreenBinding


class FirstScreen : Fragment() {

    private var binding : FragmentOnboardingFirstScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingFirstScreenBinding.inflate(inflater,container,false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding?.buttonScreen1?.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}