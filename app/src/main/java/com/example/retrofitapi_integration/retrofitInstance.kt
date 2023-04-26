package com.example.retrofitapi_integration

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object retrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://meme-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val ApiInterface by lazy {
        retrofit.create(apiInterface::class.java)
    }
}