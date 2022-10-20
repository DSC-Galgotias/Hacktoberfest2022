package com.example.irecycle.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.irecycle.MainActivity
import com.example.irecycle.R
import com.example.irecycle.repo.Datastore
import kotlinx.coroutines.launch

class AuthAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_acitivity)
        var datastore= Datastore(this)
        lifecycleScope.launch {
//            if (datastore.isLogin() == true) {
//                var intent=Intent(this@AuthAcitivity, MainActivity::class.java)
//                startActivity(intent)
//            }
        }
    }
}