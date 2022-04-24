package com.example.a2work.services

import com.example.a2work.models.AuthRequest
import com.example.a2work.models.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    fun login(
        @Body authRequest: AuthRequest
    ): Call<AuthResponse>

}