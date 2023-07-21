package com.nocountry.movie_no_country.feature_authentication.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentLoginBinding
import com.nocountry.movie_no_country.feature_authentication.login.domain.User
import org.koin.android.ext.android.get

class LoginFragment : Fragment() {
    private var binding : FragmentLoginBinding? = null
    private val auth = get<FirebaseAuth>()
    private val db = get<FirebaseFirestore>()
    private lateinit var user: User
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //findNavController().navigate(R.id.action_fragment_Login_to_homeFragment)
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        textView()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        login()
        binding?.apply {
            textViewCreateAcc.setOnClickListener {
                findNavController().navigate(R.id.action_fragment_Login_to_signupFragment)
            }
            textViewForgetPass.setOnClickListener {
                //val window = ForgetPassword()
                //window.show(parentFragmentManager,"ventana")
                ForgetPassword().show(parentFragmentManager,"ventana")
            }
            imageView3Google.setOnClickListener {
                signInGoogle()
            }
        }
        return binding?.root
    }
    private fun login(){
        binding?.apply {
            buttonLogin2.setOnClickListener {
                user = User(etEmail.text.toString(),etPassword.text.toString())
                val verification = listOf(user.email,user.password).any{it.isBlank()}
                if(verification){
                    Toast.makeText(requireContext(),"Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }else{
                    auth.signInWithEmailAndPassword(user.email,user.password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            findNavController().navigate(R.id.action_fragment_Login_to_homeFragment)
                            saveDb(user.email)
                            Toast.makeText(context,"Bienvenido: ${user.email}", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener{
                        Toast.makeText(requireContext(),"Usuario no Creado",Toast.LENGTH_SHORT).show()
                    }
                }
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
            Toast.makeText(requireContext(),"SignIn Failed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                findNavController().navigate(R.id.action_fragment_Login_to_homeFragment)
            }
            else{
                Toast.makeText(requireContext(),"Usuario no existe",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun textView(){
        val spannableString = SpannableString("Soy nuevo aqui, Crear cuenta")
        val redColorSpan = ForegroundColorSpan(Color.WHITE)
        spannableString.setSpan(redColorSpan, 0, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val blueColorSpan = ForegroundColorSpan(Color.YELLOW)
        spannableString.setSpan(blueColorSpan, 15, 28, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding?.textViewCreateAcc?.text = spannableString
    }

    private fun saveDb(email : String){
        val user = hashMapOf(
            "email" to email
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }

}