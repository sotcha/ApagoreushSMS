package com.sotcha.apagoreushsms.model

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesService {
    private const val PREFS_NAME = "SharedPreferencesService"


    fun saveUser(context: Context, user: User) {
        getPrefs(context).edit()
            .putString("name", user.name)
            .putString("address", user.address)
            .apply()
    }

    fun readUser(context: Context): User {
        val prefs = getPrefs(context)
        return User(
            prefs.getString("name", "") ?: "",
            prefs.getString("address", "") ?: ""
        )
    }


    private fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


}