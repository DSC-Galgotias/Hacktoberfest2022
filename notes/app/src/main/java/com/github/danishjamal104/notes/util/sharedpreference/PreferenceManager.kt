package com.github.danishjamal104.notes.util.sharedpreference

import android.content.Context
import android.content.SharedPreferences

abstract class PreferenceManager(var context: Context) {

    private val prefName = "com.github.code.gambit.pref"
    val pref: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor get() = pref.edit()

    fun <T> put(key: Key, value: T) {
        when (value) {
            is String -> {
                editor.putString(key.value, value).apply()
            }
            is Boolean -> {
                editor.putBoolean(key.value, value).apply()
            }
            is Int -> {
                editor.putInt(key.value, value).apply()
            }
            else -> editor.putString(key.value, value.toString()).apply()
        }
    }

    inline fun <reified T> get(key: Key, default: T? = null): T {
        return when (T::class) {
            String::class -> {
                pref.getString(key.value, default?.let { it as String } ?: "") as T
            }
            Boolean::class -> {
                pref.getBoolean(key.value, default?.let { it as Boolean } ?: false) as T
            }
            Int::class -> {
                pref.getInt(key.value, default?.let { it as Int } ?: -1) as T
            }
            else -> throw IllegalStateException("Only supports String, Boolean and Int")
        }
    }

}

enum class Key(val value: String) {
    USERID("USER-ID"),
    AUTHSTATE("IS-AUTHENTICATED")
}