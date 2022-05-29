package com.example.a2work

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.a2work.data.profile.models.Usuario
import com.example.a2work.rest.Rest
import com.example.a2work.services.UsuarioService
import com.example.a2work.utils.Validator
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private val retrofit = Rest.getInstance()
    private val retrofitGetUser = Rest.getInstance().create(UsuarioService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        btnLogin.setOnClickListener {
            login()
        }
    }

    fun login() {
        if (!Validator.emailIsFine(etEmail.text.toString())) {
            etEmail.error = "E-mail inválido!"
        } else if (!Validator.passwordIsFine(etPassword.text.toString())) {
            etPassword.error = "Senha inválida!"
        } else {
            val usuarioRequest = retrofit.create(UsuarioService::class.java)
            usuarioRequest.login(etEmail.text.toString(), etPassword.text.toString())
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            getIdUsuario()
                            startActivity(Intent(baseContext, FeedActivity::class.java))
                        } else {
                            Toast.makeText(
                                baseContext,
                                "Usuário e/ou senha estão incorretos!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    fun registrar(view: View) {
        startActivity(Intent(baseContext, CadastroActivity::class.java))
    }


    fun getIdUsuario() {
        val prefs = getSharedPreferences("ACESSO", Context.MODE_PRIVATE)
        val token = prefs.getString("jwt_token", "")
        val email = etEmail.text.toString()
        val senha = etPassword.text.toString()

        retrofitGetUser.getIdUsuario(email, senha, token)
            .enqueue(object : Callback<Usuario> {
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {

                        val idActiveUser = response.body()?.idUsuario.toString()
                        val preferences: SharedPreferences = getSharedPreferences("id_user", Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = preferences.edit()
                        editor.putString("id_user", idActiveUser)
                        editor.apply()

                    } else {
                        Log.d("Error", "Error: getIdUsuario")
                    }
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.d("Error", "onFailure: getIdUsuario")

                }

            })
    }
}