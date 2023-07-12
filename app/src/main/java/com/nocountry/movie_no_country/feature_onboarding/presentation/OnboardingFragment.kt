package com.nocountry.movie_no_country.feature_onboarding.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nocountry.movie_no_country.MainActivity
import com.nocountry.movie_no_country.databinding.FragmentOnboardingBinding
import com.nocountry.movie_no_country.feature_onboarding.presentation.screen.FirstScreen
import com.nocountry.movie_no_country.feature_onboarding.presentation.screen.SecondScreen
import com.nocountry.movie_no_country.feature_onboarding.presentation.screen.ThirdScreen

class OnboardingFragment : Fragment() {
    private var binding: FragmentOnboardingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        val fragmentList = arrayListOf(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )
        val adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
        binding?.viewPager?.adapter = adapter

        binding?.viewPager?.let {
            binding?.dots?.attachTo(
                it
            )
        }


        (activity as MainActivity).showBottomNav(false)

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}