package com.nocountry.movie_no_country.feature_authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding?.buttonLogin2?.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_Login_to_homeFragment)
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }

}