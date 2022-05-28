package com.example.a2work.services

import com.example.a2work.data.profile.models.Projeto
import retrofit2.Call
import retrofit2.http.GET

interface ProjectService {

    @GET("/2Work/projetos")
    fun getProjetos(): Call<List<Projeto>>

    @GET("/2Work/retornar-ultimos-projetos")
    fun getProjetosRecentes(): Call<List<Projeto>>

}