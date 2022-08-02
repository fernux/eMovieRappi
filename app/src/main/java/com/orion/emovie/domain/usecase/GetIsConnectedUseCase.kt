package com.orion.emovie.domain.usecase

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetIsConnectedUseCase @Inject constructor(

) {
    suspend operator fun invoke(context: Context):Boolean {
        return isNetworkAvailable(context)
    }
    private suspend fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                withContext(Dispatchers.Main) {
                    val nw      = connectivityManager.activeNetwork ?: return@withContext false
                    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return@withContext false
                    return@withContext when {
                        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                        else -> false
                    }
                }

            }
        return false
    }
}