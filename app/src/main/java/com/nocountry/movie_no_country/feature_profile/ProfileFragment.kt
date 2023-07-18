package com.nocountry.movie_no_country.feature_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentProfileBinding
import org.koin.android.ext.android.get

class ProfileFragment : Fragment() {
    private var binding : FragmentProfileBinding? = null
    private val auth = get<FirebaseAuth>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        signOut()
        return binding?.root
    }
    private fun signOut(){
        binding?.apply {
            buttonSignOut.setOnClickListener {
                auth.signOut()
                findNavController().navigate(R.id.action_profileFragment_to_fragment_Login)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}