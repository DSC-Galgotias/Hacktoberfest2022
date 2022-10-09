package com.github.danishjamal104.notes.data.repository.auth

import com.github.danishjamal104.notes.data.local.CacheDataSource
import com.github.danishjamal104.notes.data.model.User
import com.github.danishjamal104.notes.util.ServiceResult
import com.github.danishjamal104.notes.util.sharedpreference.UserPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import java.lang.Exception

class AuthRepositoryImpl
constructor(
    private val cacheDataSource: CacheDataSource,
    private val userPreferences: UserPreferences
): AuthRepository {

    override suspend fun login(account: GoogleSignInAccount): ServiceResult<User> {
        val userDisplayName = account.displayName
        val email = account.email
        if(userDisplayName == null || email == null) {
            return ServiceResult.Error("User details not found")
        }
        val user = User.getInstance(userDisplayName, email)
        return try {
            cacheDataSource.addUser(user)
            userPreferences.grantAuthentication(user)
            ServiceResult.Success(user)
        } catch (e: Exception) {
            ServiceResult.Error(""+e.localizedMessage)
        }
    }

}