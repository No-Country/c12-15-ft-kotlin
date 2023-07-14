package com.nocountry.movie_no_country.feature_authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentForgetPasswordBinding
import org.koin.android.ext.android.get

class ForgetPassword : DialogFragment() {
    private var binding : FragmentForgetPasswordBinding? = null
    private val auth = get<FirebaseAuth>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgetPasswordBinding.inflate(inflater,container,false)
        val myemail = binding?.etEmailDialog?.text.toString()
        binding?.apply {
            buttonClose.setOnClickListener {
                dismiss()
            }
            buttonSend.setOnClickListener {
                sendPasswordReset(myemail)
            }
        }
        return binding?.root
    }
    private fun sendPasswordReset(email : String){

        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(requireContext(), "Email correcto para cambiar la contraseña", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "ERROR, La cuenta no existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

}