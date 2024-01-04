package com.example.data.interceptor

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import com.example.data.exceptions.GeneralException
import com.example.data.exceptions.NoConnectivityException
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(@ApplicationContext private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (isConnected()){
            val request = chain.request()
            val response = chain.proceed(request)
            when (response.code){
                200 -> response
                else -> throw GeneralException(response.message)
            }
        } else {
            throw NoConnectivityException("NO CONNECTION")
        }
    }

    private fun isConnected(): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false
        return (capabilities.hasCapability(NET_CAPABILITY_INTERNET) && capabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_VALIDATED))
    }
}