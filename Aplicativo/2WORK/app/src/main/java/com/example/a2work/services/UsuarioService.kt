package com.example.a2work.services

import com.example.a2work.data.profile.models.Usuario
import retrofit2.Call
import retrofit2.http.*

interface UsuarioService {

    @GET("/users")
    fun list(
        @Header("Authorization") token: String?,
    ): Call<List<Usuario>>

    @POST("/2Work/cadastrar-usuario")
    fun cadastar(@Body novoUsuario: Usuario): Call<Void>

    @GET("/2Work/login-usuario/{email}/{senha}")
    fun login(@Path("email") email: String,
              @Path("senha") senha: String): Call<Void>
}