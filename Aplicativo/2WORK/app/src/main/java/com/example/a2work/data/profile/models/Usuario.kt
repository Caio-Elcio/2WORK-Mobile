package com.example.a2work.data.profile.models

data class Usuario(
    val idUsuario: Int?,
    val nomeUsuario: String?,
    val emailUsuario: String,
    val senhaUsuario: String,
    val dataNascimentoUsuario: String,
    val biografiaUsuario: String,
    val avaliacaoUsuario: Double,
    val cpfUsuario: String,
    val cidadeUsuario: String,
    val ufUsuario: String,
    val planoUsuario: String
)