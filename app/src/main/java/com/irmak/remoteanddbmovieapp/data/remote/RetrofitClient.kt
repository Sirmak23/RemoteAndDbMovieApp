package com.irmak.remoteanddbmovieapp.data.remote

import android.provider.SyncStateContract.Constants
import com.irmak.remoteanddbmovieapp.data.remote.RetrofitClient.Companion.client
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RetrofitClient {
    companion object {
        private const val TAG = "RetrofitClient"
        private var INSTANCE: Retrofit? = null

        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        fun getRetrofit(): Retrofit {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(MovieApi.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
