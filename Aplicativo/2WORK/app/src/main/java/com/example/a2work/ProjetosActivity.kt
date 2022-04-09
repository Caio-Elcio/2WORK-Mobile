package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_projetos.*

class ProjetosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projetos)
        navigationMenu()
    }

    fun navigationMenu() {
        project_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.navi_home -> {
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                }
                R.id.navi_upload -> {
                    val i = Intent(this, UploadActivity::class.java)
                    startActivity(i)
                }
                R.id.navi_projeto -> {
                    val i = Intent(this, ProjetosActivity::class.java)
                    startActivity(i)
                }
                R.id.navi_conta -> {
                    val i = Intent(this, ProfileActivity::class.java)
                    startActivity(i)
                }
                R.id.navi_planos -> {
                    val i = Intent(this, ProfileActivity::class.java)
                    startActivity(i)
                }
            }
            true
        }
    }
}