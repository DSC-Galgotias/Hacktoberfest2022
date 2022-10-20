package com.example.irecycle.repo

import android.content.Context
import androidx.datastore.core.DataStore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


const val DATASTORE_NAME = "user_details"
val Context.datastore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(DATASTORE_NAME)

class Datastore(context: Context) {
    private val appContext = context.applicationContext

    companion object {
        const val LOGIN_KEY = "LOGIN_KEY"
        const val NAME_KEY = "NAME_KEY"
        const val PHONE_NUMBER = "PHONE_NUMBER"
    }

    suspend fun saveUserDetails(key: String, value: String?) {
        val key1 = stringPreferencesKey(key)
        appContext.datastore.edit { user_details ->
            user_details[key1] = value.toString()
        }
    }

    suspend fun changeLoginState(value: Boolean) {
        val key1 = booleanPreferencesKey(LOGIN_KEY)
        appContext.datastore.edit {
            it[key1] = value
        }
    }

    suspend fun getUserDetails(key: String): String? {
        val key1 = stringPreferencesKey(key)
        return appContext.datastore.data.first()[key1]
    }

    suspend fun isLogin(): Boolean {
        val key1 = booleanPreferencesKey(LOGIN_KEY)
        return appContext.datastore.data.first()[key1] ?: false
    }

    //Login true and save all data to Datastore
    suspend fun saveToDatastore(key: String, data: String, context: Context) {
        val datastore = Datastore(context)
        datastore.saveUserDetails(key, data)
    }
}

