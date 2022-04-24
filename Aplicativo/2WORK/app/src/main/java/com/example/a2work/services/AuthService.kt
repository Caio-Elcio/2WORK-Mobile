package com.example.a2work.services

import com.example.a2work.models.AuthRequest
import com.example.a2work.models.AuthRequestSignUp
import com.example.a2work.models.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    fun login(
        @Body authRequest: AuthRequest
    ): Call<AuthResponse>

    // Muda o endpoint pro mesmo que ta no backend
    @POST("/users")
    fun addUser(
        @Body authRequestSignUp: AuthRequestSignUp
    ): Call<AuthRequestSignUp>
}