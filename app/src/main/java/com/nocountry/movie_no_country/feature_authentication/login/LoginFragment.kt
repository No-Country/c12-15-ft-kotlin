package com.nocountry.movie_no_country.feature_authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentLoginBinding
import kotlinx.coroutines.delay

class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding? = null
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        login()
        signup()
        return binding?.root
    }

    private fun login(){
        auth = FirebaseAuth.getInstance()
        binding?.buttonLogin2?.setOnClickListener {
            val email = binding?.etEmail?.text.toString()
            val password = binding?.etPassword?.text.toString()

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_fragment_Login_to_homeFragment)
                }
            }.addOnFailureListener{
                Toast.makeText(requireContext(),"Error",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun signup(){
        auth = FirebaseAuth.getInstance()
        binding?.buttonLogin2?.setOnClickListener {
            val email = binding?.etEmail?.text.toString()
            val password = binding?.etPassword?.text.toString()
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    findNavController().navigate(R.id.action_fragment_Login_to_homeFragment)
                }
            }.addOnFailureListener {
                Toast.makeText(requireContext(),"No se pudo Crear el usuario",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }

}