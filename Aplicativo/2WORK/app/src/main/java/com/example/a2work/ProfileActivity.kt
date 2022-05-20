package com.example.a2work

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.a2work.profile.SlideActivity
import kotlinx.android.synthetic.main.activity_profile.*
import android.content.DialogInterface
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.a2work.data.profile.models.Usuario
import com.example.a2work.rest.Rest
import com.example.a2work.services.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private val retrofit = Rest.getInstance()
    private val retro = Rest.getInstance().create(UsuarioService::class.java)
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

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

    fun editarPerfil(view: View) {
        startActivity(Intent(baseContext, UpdateProfileActivity::class.java))
    }

    fun showAlertDialog(view: View) {
//        getIdUsuario()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("2WORK")
        builder.setMessage("Tem certeza que deseja sair?")
        builder.setPositiveButton("Sim", { dialogInterface: DialogInterface, i: Int ->
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        })
        builder.setNegativeButton("Não", { dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }

    fun getUser() {
        val request = retrofit.create(UsuarioService::class.java)
        request.list("").enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful) {
                    tvNameUser.text = response.body()?.first()?.nomeUsuario
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
            }
        })
    }

    fun getIdUsuario() {
        val prefs = getSharedPreferences("ACESSO", Context.MODE_PRIVATE)
        val token = prefs.getString("jwt_token", "")
        val email = etEmail.text.toString()
        val senha = etPassword.text.toString()

        retro.getIdUsuario(email, senha, token)
            .enqueue(object : Callback<Usuario> {
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        val tvNome = TextView(baseContext)
                        tvNome.text = response.body()?.nomeUsuario
                        findViewById<TextView>(R.id.nome_escrito).text = tvNome.toString()
                    } else {
                        Toast.makeText(
                            baseContext,
                            response.message(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Toast.makeText(
                        baseContext,
                        t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
    }

}