package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a2work.profile.SlideActivity
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
            bottom_navigation_home.setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.navi_home -> {
                    }
                    R.id.navi_upload -> {
                        startActivity(Intent(this, UploadActivity::class.java))
                    }
                    R.id.navi_projeto -> {
                        startActivity(Intent(this, ProjetosActivity::class.java))
                    }
                    R.id.navi_conta -> {
                        startActivity(Intent(this, ProfileActivity::class.java))
                    }
                    R.id.navi_planos -> {
                        startActivity(Intent(this, SlideActivity::class.java))
                    }
                }
                true
            }
    }

    fun upload(view: View){
        startActivity(Intent(baseContext, UploadActivity::class.java))
    }

}