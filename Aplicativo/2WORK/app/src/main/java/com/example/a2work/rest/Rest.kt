package com.example.a2work.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    // var baseUrl = "http://10.0.2.2:8080"
    val baseUrl = "http://10.18.37.12:8080/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}