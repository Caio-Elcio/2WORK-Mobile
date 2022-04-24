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
import com.example.a2work.models.AuthResponse
import com.example.a2work.models.Usuario
import com.example.a2work.rest.Rest
import com.example.a2work.services.AuthService
import com.example.a2work.services.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private val retrofit = Rest.getInstance()

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

        getUser()

    }

    fun editar(view: View){
        startActivity(Intent(baseContext, UpdateProfileActivity::class.java))
    }

    fun showAlertDialog(view: View) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("2WORK")
        builder.setMessage("Tem certeza que deseja sair?")
        builder.setPositiveButton("Sim", { dialogInterface: DialogInterface, i: Int ->
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        })
        builder.setNegativeButton("Não", { dialogInterface: DialogInterface, i: Int ->})
        builder.show()
    }

    fun getUser(){
        val request = retrofit.create(UsuarioService::class.java)

        request.list("").enqueue(object : Callback<List<Usuario>>{
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful){
                    tvNameUser.text = response.body()?.first()?.nomeUsuario
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {

            }


        })
    }

}