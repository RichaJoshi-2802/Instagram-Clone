package com.example.instagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instagramclone.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.SignUpButton.setOnClickListener {
            if((binding.name.editText.toString() == "") or
                (binding.email.editText.toString() == "") or
                (binding.name.editText.toString() == "")
            ){
                Toast.makeText(this@SignUpActivity,"Please fill the all information", Toast.LENGTH_SHORT).show()
            }
        }
    }
}