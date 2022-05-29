package com.example.a2work.data.profile.models

data class Projeto(
    val idProjeto: Int?,
    val tituloProjeto: String?,
    val imagemProjeto: String?,
    var descricaoProjeto: String?,
    var nomeUsuario: String?,
    var primeiraLetraNome: String?,
    val dataHoraProjeto: String?,
    val totalVisualizacoesProjeto: Int?,
    val totalCurtidasProjeto: Int?,
//    val primeiraPergunta: Boolean,
//    val segundaPergunta: Boolean,
//    val terceiraPergunta: Boolean,
    val fkUsuario: Int?
)
