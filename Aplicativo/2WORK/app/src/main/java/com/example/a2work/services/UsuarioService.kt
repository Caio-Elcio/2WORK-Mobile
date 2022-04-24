package com.example.a2work.services

import com.example.a2work.models.Usuario
import retrofit2.Call
import retrofit2.http.*

interface UsuarioService {

    @GET("/usuarios")
    fun list(
        @Header("Authorization") token: String?,
    ): Call<List<Usuario>>

    @GET("/usuarios/{id}")
    fun getById(
        @Path("id") id: Long,
        @Header("Authorization") token: String?,
    ): Call<Usuario>

    @DELETE("/usuarios/{id}")
    fun remove(
        @Path("id") id: Int,
        @Header("Authorization") token: String?,
    ): Call<Void>

    @POST("/usuarios")
    fun save(
        @Header("Authorization") token: String?,
    )

}