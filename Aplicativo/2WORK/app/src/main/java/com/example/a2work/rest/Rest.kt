package com.example.a2work.rest

import com.example.a2work.services.UsuarioService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Time
import java.util.concurrent.TimeUnit

class Rest {

    companion object {

        var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        @JvmStatic
        fun getInstance(): Retrofit {
            return Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(UsuarioService.BASE_URL_MOCK)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}

