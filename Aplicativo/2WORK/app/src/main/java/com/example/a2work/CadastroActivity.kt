package com.example.a2work

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.a2work.data.profile.models.Usuario
import com.example.a2work.rest.Rest
import com.example.a2work.services.UsuarioService
import kotlinx.android.synthetic.main.activity_cadastro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {

    private val retrofit = Rest.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    fun termos(view: View) {
        // Coloca os textos em um arquivo string
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Lei Geral de Proteção de Dados")
        builder.setMessage(
            "\n" +
                    "Este aplicativo e seu conteúdo (\"2WORK\") são controlados e operados pela própria 2WORK. Todos os direitos reservados.\n" +
                    "\n" +
                    "Estes termos de uso têm por objeto definir as regras a serem seguidas para a utilização do 2WORK (\"Termos de Uso\"), sem prejuízo da aplicação da legislação vigente.\n" +
                    "\n" +
                    "Ao utilizar o 2WORK, você automaticamente concorda com estes Termos de Uso,   responsabilizando-se integralmente por todos e quaisquer atos praticados por você, caso você não concorde com qualquer Termos e Condições estabelecidos, você não deve utilizar o aplicativo 2WORK.\n" +
                    "\n" +
                    "Você também concorda com os Termos descritos em nossa Política de Privacidade. Para acessá-la, clique aqui (direitos2work.com)." +
                    ""
        )
        builder.setPositiveButton("Fechar", { dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }

    fun signUp(view: View) {
        val usuarioRequest = retrofit.create(UsuarioService::class.java)
        val novoUsuario = Usuario(
            idUsuario = null,
            nomeUsuario = etName.text.toString(),
            emailUsuario = etEmail.text.toString(),
            senhaUsuario = etPassword.text.toString(),
            dataNascimentoUsuario = etData.text.toString(),
            biografiaUsuario = "Escreva algo sobre você aqui!",
            avaliacaoUsuario = 0.0,
            cpfUsuario = etCpf.text.toString(),
            cidadeUsuario = etCidade.text.toString(),
            ufUsuario = etUf.text.toString(),
            planoUsuario = "Basic"
        )
        usuarioRequest.cadastar(novoUsuario).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(baseContext, "Usuário cadastrado!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext, LoginActivity::class.java))
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

    fun enviarLogin(view: View) {
        startActivity(Intent(baseContext, LoginActivity::class.java))
    }
}