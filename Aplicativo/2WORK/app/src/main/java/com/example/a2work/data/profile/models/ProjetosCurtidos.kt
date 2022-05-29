package com.example.a2work.data.profile.models

data class ProjetosCurtidos(
    val idCurtida: Int,
    val dataHoraCurtido: String?,
    val usuario: Usuario,
    var projeto: Projeto,
)