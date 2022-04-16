package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a2work.profile.SlideActivity
import kotlinx.android.synthetic.main.activity_projetos.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun entrar(view: View){
        startActivity(Intent(baseContext, LoginActivity::class.java))
    }

    fun cadastro(view: View){
        startActivity(Intent(baseContext, CadastroActivity::class.java))
    }

}