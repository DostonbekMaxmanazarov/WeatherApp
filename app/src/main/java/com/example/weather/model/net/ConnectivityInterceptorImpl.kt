package com.example.weather.model.net

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {
    private val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw IOException()
        return chain.proceed(chain.request())

    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netWorkInfo = connectivityManager.activeNetworkInfo

        return netWorkInfo != null && netWorkInfo.isConnected
    }
}