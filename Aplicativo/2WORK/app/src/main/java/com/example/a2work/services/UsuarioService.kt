package com.example.a2work.services

import com.example.a2work.models.Usuario
import retrofit2.Call
import retrofit2.http.*

interface UsuarioService {

    companion object {
        val BASE_URL_MOCK = "https://6265911994374a2c5071836b.mockapi.io/"

        val BASE_URL = "https://6265911994374a2c5071836b.mockapi.io/"
        /* Em caso de uso no celular:
            http://10.18.33.178:3000/
         */
    }

    @GET("/users")
    fun list(
        @Header("Authorization") token: String?,
    ): Call<List<Usuario>>

    @GET("/users/{id}")
    fun getById(
        @Path("id") id: String,
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