package com.example.a2work

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    fun cadastro(view: View){
        startActivity(Intent(baseContext, FeedActivity::class.java))
    }

    fun termos(view: View) {

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

}