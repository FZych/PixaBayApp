package com.filipzych.pixabay.network

import com.filipzych.pixabay.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitProvider {
    fun provideRetrofit(): Retrofit
}

class RetrofitProviderImpl(private val okHttpClient: OkHttpClient, private val gson: Gson) :
    RetrofitProvider {

    override fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}