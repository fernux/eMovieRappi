package com.orion.emovie.utilities

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_NAME = "com.orion.emovie"
    val SHARED_NAME = "shared_name"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var name: Int
        get() = prefs.getInt(SHARED_NAME, 0)
        set(value) = prefs.edit().putInt(SHARED_NAME, value).apply()
}