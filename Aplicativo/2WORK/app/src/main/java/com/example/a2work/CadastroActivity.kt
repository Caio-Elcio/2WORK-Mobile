package com.example.a2work

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.a2work.models.AuthRequest
import com.example.a2work.models.AuthRequestSignUp
import com.example.a2work.rest.Rest
import com.example.a2work.services.AuthService
import kotlinx.android.synthetic.main.activity_cadastro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {

    private val retrofit = Rest.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btnSignUp.setOnClickListener {
            signUp()
        }
    }

    fun termos(view: View) {

        // Coloca os textos em um arquivo string
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Lei Geral de Proteção de Dados")
        builder.setMessage("\n" +
                "Este aplicativo e seu conteúdo (\"2WORK\") são controlados e operados pela própria 2WORK. Todos os direitos reservados.\n" +
                "\n" +
                "Estes termos de uso têm por objeto definir as regras a serem seguidas para a utilização do 2WORK (\"Termos de Uso\"), sem prejuízo da aplicação da legislação vigente.\n" +
                "\n" +
                "Ao utilizar o 2WORK, você automaticamente concorda com estes Termos de Uso,   responsabilizando-se integralmente por todos e quaisquer atos praticados por você, caso você não concorde com qualquer Termos e Condições estabelecidos, você não deve utilizar o aplicativo 2WORK.\n" +
                "\n" +
                "Você também concorda com os Termos descritos em nossa Política de Privacidade. Para acessá-la, clique aqui (direitos2work.com)." +
                "")
        builder.setPositiveButton("Fechar", { dialogInterface: DialogInterface, i: Int ->})
        builder.show()
    }

    fun signUp(){
        val request = retrofit
            .create(AuthService::class.java)

        // Adiciona os outros campos aqui e os parametros no AuthRequestSignUp
        val authRequest = AuthRequestSignUp(
            etName.text.toString(),
            etEmail.text.toString(),
            etPassword.text.toString()
        )

        request.addUser(authRequest).enqueue(object : Callback<AuthRequestSignUp>{
            override fun onResponse(
                call: Call<AuthRequestSignUp>,
                response: Response<AuthRequestSignUp>
            ) {
                if (response.isSuccessful) {
                    // Coloca um loading antes de ir para o feed, pq demora pra fazer a requisição e fica estranho quando clica no botão e não acontece nada
                    startActivity(Intent(baseContext, FeedActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<AuthRequestSignUp>, t: Throwable) {
                Toast.makeText(baseContext, "Erro", Toast.LENGTH_SHORT).show()
            }

        })
    }

}