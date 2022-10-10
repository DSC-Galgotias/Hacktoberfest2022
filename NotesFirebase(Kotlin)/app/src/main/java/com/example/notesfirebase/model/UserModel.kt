package com.example.notesfirebase.model

import java.text.SimpleDateFormat
import java.util.*

data class UserModel(
    val name: String? = "",
    val email: String? = "",
    val note: String? = "",
    val title: String? = "",
    val currentTime: String = SimpleDateFormat("dd-MM-yyyy, HH:mm", Locale.getDefault()).format(Date())
)
