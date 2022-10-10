package com.example.notesfirebase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.notesfirebase.MainActivity
import com.example.notesfirebase.R
import com.example.notesfirebase.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        val user = FirebaseAuth.getInstance().currentUser
        Handler(Looper.getMainLooper()).postDelayed({
           if (user == null) {
               startActivity(Intent(this, LoginActivity::class.java))
           } else {
               startActivity(Intent(this, MainActivity::class.java))
           }
            finish()
        }, 3000)
    }
}