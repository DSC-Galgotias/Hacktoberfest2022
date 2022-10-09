package com.github.danishjamal104.notes.util.sharedpreference

import android.content.Context
import com.github.danishjamal104.notes.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext

class UserPreferences
constructor(@ApplicationContext context: Context): PreferenceManager(context) {

    private fun setUserId(id: String) {
        put(Key.USERID, id)
    }

    private fun setAuthenticated(value: Boolean) = put(Key.AUTHSTATE, value)

    fun isAuthenticated() = get<Boolean>(Key.AUTHSTATE)

    fun getUserId() = get<String>(Key.USERID)

    fun grantAuthentication(user: User) {
        setUserId(user.id)
        setAuthenticated(true)
    }

    fun revokeAuthentication() {
        setUserId("")
        setAuthenticated(false)
    }

}