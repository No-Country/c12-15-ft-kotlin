package com.nocountry.movie_no_country.feature_onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nocountry.movie_no_country.databinding.FragmentOnboardingBinding
import com.nocountry.movie_no_country.feature_onboarding.screen.FirstScreen
import com.nocountry.movie_no_country.feature_onboarding.screen.SecondScreen
import com.nocountry.movie_no_country.feature_onboarding.screen.ThirdScreen

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
        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )
        val adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
        binding?.ViewPager?.adapter = adapter
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}