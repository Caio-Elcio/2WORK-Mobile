package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View){
        startActivity(Intent(baseContext, FeedActivity::class.java))
    }

    fun registrar(view: View){
        startActivity(Intent(baseContext, CadastroActivity::class.java))
    }

}