package com.example.githubviewer.model

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


private const val SHARED_PREFS_NAME = "app_data"

private const val KEY_NAME = "name"
private const val KEY_TOKEN = "token"

class KeyValueStorage @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPref = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    var authToken: String?
        get() = sharedPref.getString(KEY_TOKEN, null)
        set(token) {
            sharedPref.edit().putString(KEY_TOKEN, token).apply()
        }
    var userName: String?
        get() = sharedPref.getString(KEY_NAME, null)
        set(name) {
            sharedPref.edit().putString(KEY_NAME, name).apply()
        }
}