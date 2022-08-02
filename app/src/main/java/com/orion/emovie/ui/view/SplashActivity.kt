package com.orion.emovie.ui.view


import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orion.emovie.EmovieApplication
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var isConnected: Boolean

                val connectivityManager = this.applicationContext.getSystemService(
                    CONNECTIVITY_SERVICE
                ) as ConnectivityManager
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        isConnected= activeNetwork != null && activeNetwork.isConnectedOrConnecting

        if(EmovieApplication.prefs.name == 0) {
            if(isConnected) {
                overridePendingTransition(0, 0)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                ConnectivityDialogFragment().show(supportFragmentManager,"")
            }
        }
        else{
            overridePendingTransition(0, 0)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}