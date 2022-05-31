package com.example.a2work.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    private val baseUrl = "http://10.0.2.2:8080/"
    // "http://192.168.0.109:8080/"
    // Em caso de uso no celular:
    // private val baseUrl = "http://10.18.6.121:3000/"

    fun getInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}