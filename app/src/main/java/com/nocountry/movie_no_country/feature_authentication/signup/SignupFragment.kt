package com.nocountry.movie_no_country.feature_authentication.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentSignupBinding
import com.nocountry.movie_no_country.feature_authentication.login.domain.User
import com.nocountry.movie_no_country.feature_authentication.signup.domain.NewUser
import org.koin.android.ext.android.get

class SignupFragment : Fragment() {
    private var binding: FragmentSignupBinding? = null
    private val auth = get<FirebaseAuth>()
    private lateinit var user: NewUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        signup()
        return binding?.root
    }
    private fun signup(){
        binding?.apply {
            buttonCreateAccount.setOnClickListener {
                user = NewUser(
                    etEmailSignup.text.toString(),
                    etPasswordSignup.text.toString(),
                    etNameSignup.text.toString(),
                    etLastnameSignup.text.toString())
                val verification = listOf(user.email, user.password,user.lastName,user.name).any{it.isBlank()}
                if(verification){
                    Toast.makeText(requireContext(),"Por favor llene todos campos", Toast.LENGTH_LONG).show()
                }else {
                    auth.createUserWithEmailAndPassword(user.email,user.password).addOnCompleteListener {
                        if(it.isSuccessful){
                            findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                            Toast.makeText(context,"Bienvenido", Toast.LENGTH_LONG).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(requireContext(),"Hubo un error intenta nuevamente",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}