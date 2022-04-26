package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
            etEmail.error = "E-mail inválido"
        } else if (!Validator.passwordIsFine(etPassword.text.toString())) {
            etPassword.error = "Senha inválida"
        } else {
            val usuarioRequest = retrofit.create(UsuarioService::class.java)
            usuarioRequest.login("etEmail", "etPassword").enqueue(object: Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful){
                        startActivity(Intent(baseContext, FeedActivity::class.java))
                    } else {
                        Toast.makeText(baseContext, "Usuario ou senha invalido", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    fun registrar(view: View){
        startActivity(Intent(baseContext, CadastroActivity::class.java))
    }

}