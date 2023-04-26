package com.example.retrofitapi_integration

import retrofit2.Call
import retrofit2.http.GET

interface apiInterface {
        @GET("/gimme")
        fun getData(): Call<responseDataClass>


}