package com.example.mealsoverviewapplication.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BaseUrl.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private  val retrofit_1 : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BaseUrl.baseUrl_1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val api_1 : ApiService by lazy {
        retrofit_1.create(ApiService::class.java)
    }
}