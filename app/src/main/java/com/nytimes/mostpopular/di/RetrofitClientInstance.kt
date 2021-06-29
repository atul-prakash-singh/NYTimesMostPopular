package com.nytimes.mostpopular.di

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {

    private const val BASE_URL = "https://api.nytimes.com/svc/"
    private const val CACHE_SIZE = 10 * 1024 * 1024

    fun getRetrofitInstance(context: Context): Retrofit {
        val cache = Cache(context.cacheDir, CACHE_SIZE.toLong())

        val okHttpClient = OkHttpClient.Builder().cache(cache).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}