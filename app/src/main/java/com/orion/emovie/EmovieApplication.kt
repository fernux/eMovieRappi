package com.orion.emovie

import android.app.Application
import com.orion.emovie.utilities.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmovieApplication : Application(){
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}