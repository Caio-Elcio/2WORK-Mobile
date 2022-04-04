package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_projetos.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        navigationMenu()
    }

    fun navigationMenu(){
        project_bottom_navigation?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_home -> {
                    Toast.makeText(this, "Qualquer coisa", Toast.LENGTH_SHORT).show()
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