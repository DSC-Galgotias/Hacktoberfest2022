package com.github.danishjamal104.notes.data.model

import com.github.danishjamal104.notes.util.toSHA1

data class User (
    var id: String,
    var username: String,
    var email: String
) {
    companion object {

        fun getInstance(username: String, email: String): User {
            val id = email.toSHA1()
            return User(id, username, email)
        }

    }
}