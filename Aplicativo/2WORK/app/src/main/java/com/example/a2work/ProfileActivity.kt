package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plans.SlideActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottom_navigation_perfil.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_home -> {
                    startActivity(Intent(this, FeedActivity::class.java))
                }
                R.id.navi_upload -> {
                    startActivity(Intent(this, UploadActivity::class.java))
                }
                R.id.navi_projeto -> {
                    startActivity(Intent(this, ProjetosActivity::class.java))
                }
                R.id.navi_conta -> {
                }
                R.id.navi_planos -> {
                    startActivity(Intent(this, SlideActivity::class.java))
                }
            }
            true
        }
    }
}