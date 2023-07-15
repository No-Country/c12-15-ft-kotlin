package com.nocountry.movie_no_country.feature_authentication.login

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.nocountry.movie_no_country.databinding.FragmentForgetPasswordBinding
import com.nocountry.movie_no_country.feature_authentication.login.domain.User
import org.koin.android.ext.android.get

class ForgetPassword() : DialogFragment() {
    private var binding : FragmentForgetPasswordBinding? = null
    private val auth = get<FirebaseAuth>()
    private lateinit var user: User
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentForgetPasswordBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding?.root)

        binding?.apply {
            buttonSend.setOnClickListener {
                var email = etEmailDialog.text.toString()
                if(email.isBlank()){
                    Toast.makeText(requireContext(),"Ingrese su email a recuperar", Toast.LENGTH_LONG).show()
                }else{
                    sendPasswordReset(binding?.etEmailDialog?.text.toString())
                }
            }
            buttonClose.setOnClickListener {
                dismiss()
            }
        }
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
    private fun sendPasswordReset(email : String){
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(requireContext(), "Correo enviado a: ${email}", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "La cuenta no existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

}