package com.example.a2work.services

import com.example.a2work.data.profile.models.Projeto
import retrofit2.Call
import retrofit2.http.*

interface ProjectService {

    @GET("/2Work/projetos")
    fun getProjetos(): Call<List<Projeto>>

    @GET("/2Work/retornar-ultimos-projetos")
    fun getProjetosRecentes(): Call<List<Projeto>>

    @Headers("Content-Type: application/json")
    @POST("/2Work/postar-projeto/{idUsuario}")
    fun uploadProject(
        @Path("idUsuario") idUsuario: String,
        @Body newProject: Projeto
    ): Call<Void>

}