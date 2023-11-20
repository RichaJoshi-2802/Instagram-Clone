package com.example.instagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val text = "<font color=#FF000000>Don't have an account?</font> <font color=#1E88E5>Signup</font>"
        //binding.login.text = Html.fromHtml(text)
    }
}