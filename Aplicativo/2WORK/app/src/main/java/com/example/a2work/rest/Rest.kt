package com.example.a2work.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    private val baseUrl = "http://10.0.2.2:3000/"
    /*  Em caso de uso no celular:
        http://10.18.33.178:3000/
     */
    fun getInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

