package com.example.a2work.services

import com.example.a2work.data.profile.models.Projeto
import com.example.a2work.data.profile.models.ProjetosCurtidos
import com.example.a2work.data.profile.models.Usuario
import retrofit2.Call
import retrofit2.http.*

interface ProjectService {

    @GET("/2Work/projetos")
    fun getProjetos(): Call<List<Projeto>>

    @GET("/2Work/ver-projeto/{idProjeto}")
    fun verProjeto(
        @Path("idProjeto") idProjeto: Int
    ): Call<Projeto>

    @GET("/2Work/retornar-ultimos-projetos")
    fun getProjetosRecentes(): Call<List<Projeto>>

    @GET("/2Work/meus-projetos/{idUsuario}")
    fun getProjectsByUser(
        @Path("idUsuario") idUsuario: Int
    ): Call<List<Projeto>>

    @Headers("Content-Type: application/json")
    @POST("/2Work/postar-projeto/{idUsuario}")
    fun uploadProject(
        @Path("idUsuario") idUsuario: String,
        @Body newProject: Projeto
    ): Call<Void>

    @POST("/2Work/curtir-projeto/{idProjeto}/{idUsuario}")
    fun likeProject(
        @Path("idProjeto") idProjeto: String,
        @Path("idUsuario") idUsuario: String,
        @Body project: ProjetosCurtidos
    ): Call<Void>
}