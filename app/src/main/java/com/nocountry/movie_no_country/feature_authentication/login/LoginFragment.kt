package com.nocountry.movie_no_country.feature_authentication.login

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentLoginBinding
import com.nocountry.movie_no_country.feature_authentication.login.domain.User
import org.koin.android.ext.android.get

class LoginFragment : Fragment() {
    private var binding : FragmentLoginBinding? = null
    private val auth = get<FirebaseAuth>()
    private lateinit var user: User
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(),gso)
        login()
        binding?.apply {
            textViewCreateAcc.setOnClickListener {
                findNavController().navigate(R.id.action_fragment_Login_to_signupFragment)
            }
            textViewForgetPass.setOnClickListener {
                //val window = ForgetPassword()
                //window.show(parentFragmentManager,"ventana")
                ForgetPassword(onSubmitClickListener = {email->
                    Toast.makeText(requireContext(),"Envio: $email",Toast.LENGTH_LONG).show()}).show(parentFragmentManager,"ventana")
            }
            imageView3Google.setOnClickListener {
                signInGoogle()
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
    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResult(task)
        }

    }

    private fun handleResult(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account :GoogleSignInAccount?= task.result
            if(account != null){
                updateUI(account)
            }
        }
        else{
            Toast.makeText(requireContext(),"SignIn Failed",Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                findNavController().navigate(R.id.action_fragment_Login_to_homeFragment)
            }
            else{
                Toast.makeText(requireContext(),"Usuario no existe",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }

}