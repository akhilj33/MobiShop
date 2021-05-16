package com.example.mobishop.data.sources.internet

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

interface InternetSource {
    fun hasInternetConnected(): Boolean
}

class InternetSourceImpl(private val context: Context) : InternetSource {

    private val TAG = "INTERNET SOURCE"
    private val REACHABILITY_SERVER = "https://www.google.com"

    override fun hasInternetConnected(): Boolean {
        if (hasNetworkAvailable()) {
            try {
                val connection = URL(REACHABILITY_SERVER).openConnection() as HttpURLConnection
                connection.setRequestProperty("User-Agent", "ConnectionTest")
                connection.setRequestProperty("Connection", "close")
                connection.connectTimeout = 1000 // configurable
                connection.connect()
                return (connection.responseCode == 200)
            } catch (e: IOException) {
                Log.e(TAG, "Error checking internet connection", e)
            }
        } else {
            Log.w(TAG, "No network available!")
        }
        Log.d(TAG, "hasInternetConnected: false")
        return false
    }

    private fun hasNetworkAvailable(): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return (network != null)
    }
}

