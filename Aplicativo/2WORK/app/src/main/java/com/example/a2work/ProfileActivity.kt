package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.*
import com.example.a2work.profile.SlideActivity
import kotlinx.android.synthetic.main.activity_profile.*
import android.content.DialogInterface




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

    fun editar(view: View){
        startActivity(Intent(baseContext, UpdateProfileActivity::class.java))
    }

    fun showAlertDialog(view: View) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("2WORK")
        builder.setMessage("Deseja se deslogar do aplicativo?")
        builder.setPositiveButton("Sim", { dialogInterface: DialogInterface, i: Int ->
            finish()
        })
        builder.setNegativeButton("Não", { dialogInterface: DialogInterface, i: Int ->})
        builder.show()
    }

}