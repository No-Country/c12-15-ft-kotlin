package com.nocountry.movie_no_country.feature_onboarding.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {
    private var binding : FragmentThirdScreenBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdScreenBinding.inflate(inflater,container,false)
        binding?.buttonLogin?.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_fragment_Login)
            onBoardingFinished()
        }
        return binding?.root
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}