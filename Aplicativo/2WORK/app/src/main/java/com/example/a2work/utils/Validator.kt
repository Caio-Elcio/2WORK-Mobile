package com.example.a2work.utils

object Validator {

    fun emailIsFine(email: String?): Boolean {
        return when {
            email.isNullOrEmpty() -> false
            email.indexOf("@") == -1 -> false
            else -> true
        }
    }

    fun passwordIsFine(password: String?): Boolean {
        return when {
            password.isNullOrEmpty() -> false
            password.length < 6 -> false
            else -> true
        }
    }
}