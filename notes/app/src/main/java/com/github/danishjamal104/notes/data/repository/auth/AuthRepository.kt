package com.github.danishjamal104.notes.data.repository.auth

import com.github.danishjamal104.notes.data.model.User
import com.github.danishjamal104.notes.util.ServiceResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface AuthRepository {
    suspend fun login(account: GoogleSignInAccount): ServiceResult<User>
}