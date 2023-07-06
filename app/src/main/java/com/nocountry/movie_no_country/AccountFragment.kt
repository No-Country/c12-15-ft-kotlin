package com.nocountry.movie_no_country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nocountry.movie_no_country.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding : FragmentAccountBinding? = null
    private val binding get() =  _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root
        setupNavigation()
    }

    private fun setupNavigation(){
        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_account_Fragment_to_fragment_Login)
        }
    }
}