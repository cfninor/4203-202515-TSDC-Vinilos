package com.example.appvinilos.network

import com.example.appvinilos.network.VinylsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Replace with your computer's IP address on the same Wi-Fi network
    private const val BASE_URL = "http://192.168.1.46:3000/"

    val instance: VinylsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(VinylsApiService::class.java)
    }
}