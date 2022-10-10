package com.example.notesfirebase.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesfirebase.MainActivity
import com.example.notesfirebase.R
import com.example.notesfirebase.databinding.ActivityRegisterBinding
import com.example.notesfirebase.model.UserModel
import com.example.notesfirebase.utlis.Config
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveData.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        if (binding.userName.text.toString().isEmpty()) {
            binding.userName.error = "Please enter your name"
        } else if (binding.userEmail.text.toString().isEmpty()) {
            binding.userEmail.error = "Please enter your email"
        } else if (!binding.termsCondition.isChecked) {
            Toast.makeText(
                this, "Please accept terms and conditions",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            storeData()
        }

    }

    private fun storeData() {
        Config.showDialog(this)

        val data = UserModel(
            name = binding.userName.text.toString(),
            email = binding.userEmail.text.toString()
        )
        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.phoneNumber!!)
            .setValue(data)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Config.hideDialog()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    Toast.makeText(
                        this, "User Registered successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Config.hideDialog()
                    Toast.makeText(
                        this, "Error: ${it.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}