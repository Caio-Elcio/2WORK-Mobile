package com.example.a2work.models

import java.util.*

data class Usuario(
    val idUsuario: String,
    val nomeUsuario: String?,
    val emailUsuario: String,
    val senhaUsuario: String,
    val dataNascimentoUsuario: Date,
    val biografiaUsuario: String,
    val avaliacaoUsuario: Int,
    val cpfUsuario: Long,
    val cidadeUsuario: String,
    val ufUsuario: String,
    val planoUsuario: String,
)
