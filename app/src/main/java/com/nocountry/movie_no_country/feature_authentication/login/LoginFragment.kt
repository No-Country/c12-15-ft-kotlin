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
import com.nocountry.movie_no_country.feature_authentication.login.domain.User
import org.koin.android.ext.android.get

class LoginFragment : Fragment() {
    private var binding : FragmentLoginBinding? = null
    private val auth = get<FirebaseAuth>()
    private lateinit var user: User
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
        binding?.apply {
            textViewCreateAcc.setOnClickListener {
                findNavController().navigate(R.id.action_fragment_Login_to_signupFragment)
            }
            textViewForgetPass.setOnClickListener {
                val window = ForgetPassword()
                window.show(parentFragmentManager,"ventana")
            }
        }
        return binding?.root
    }
    private fun login(){
        binding?.buttonLogin2?.setOnClickListener {
            user = User(
                binding?.etEmail?.text.toString(),
                binding?.etPassword?.text.toString())
            auth.signInWithEmailAndPassword(user.email,user.password).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_fragment_Login_to_homeFragment)
                }
            }.addOnFailureListener{
                Toast.makeText(requireContext(),"Usuario no existe",Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }

}