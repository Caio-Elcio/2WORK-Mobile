package com.example.a2work

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.a2work.models.AuthRequest
import com.example.a2work.models.AuthResponse
import com.example.a2work.rest.Rest
import com.example.a2work.services.AuthService
import com.example.a2work.utils.Validator
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

    }

    fun login(view: View) {
        if (!Validator.emailIsFine(etEmail.text.toString())) {
            etEmail.error = "E-mail inválido"
        } else if (!Validator.passwordIsFine(etPassword.text.toString())) {
            etPassword.error = "Senha inválida"
        } else {
            val request = retrofit
                .create(AuthService::class.java)
            val authRequest = AuthRequest(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
            request.login(authRequest).enqueue(object: Callback<AuthResponse>{
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful){
                        println(response.body()?.token)
                        val editor = getSharedPreferences(
                            "ACESSO",
                            Context.MODE_PRIVATE
                        ).edit()
                        editor.putString("jwt_token", response.body()?.token)
                        editor.apply()
                        startActivity(
                            Intent(
                                baseContext,
                                MainActivity::class.java
                            )
                        )
                    }else if (response.code() == 403){
                        Toast.makeText(
                            baseContext,
                            "Usuário e ou senha incorretos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(
                        baseContext,
                        "Erro, e-mail ou senha inválidos!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    fun registrar(view: View){
        startActivity(Intent(baseContext, CadastroActivity::class.java))
    }

}