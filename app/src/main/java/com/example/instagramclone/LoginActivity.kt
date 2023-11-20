package com.example.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import com.example.instagramclone.databinding.ActivityLoginBinding
import com.example.instagramclone.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener {
            if((binding.loginEmail.editText?.text.toString() == "") or
                (binding.loginPassword.editText?.text.toString() == "")){
                    Toast.makeText(this@LoginActivity,"Please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                val user = User(binding.loginEmail.editText?.text.toString(),binding.loginPassword.editText?.text.toString())

                Firebase.auth.signInWithEmailAndPassword(user.email!!, user.password!!).addOnCompleteListener {
                    if(it.isSuccessful){
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.signup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            finish()
        }
    }
}


















































