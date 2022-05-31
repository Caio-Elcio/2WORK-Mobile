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
import android.content.SharedPreferences
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a2work.adapters.ProjetoAdapter
import com.example.a2work.adapters.ProjetoUserAdapter
import com.example.a2work.data.profile.models.Projeto
import com.example.a2work.data.profile.models.Usuario
import com.example.a2work.rest.Rest
import com.example.a2work.services.ProjectService
import com.example.a2work.services.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private val retro = Rest.getInstance().create(UsuarioService::class.java)
    private val retrofitProjeto = Rest.getInstance().create(ProjectService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        getIdUsuario()

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

    fun editarPerfil(view: View) {
        startActivity(Intent(baseContext, UpdateProfileActivity::class.java))
    }

    fun showAlertDialog(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("2WORK")
        builder.setMessage("Tem certeza que deseja sair?")
        builder.setPositiveButton("Sim") { dialogInterface: DialogInterface, i: Int ->
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("Não") { dialogInterface: DialogInterface, i: Int -> }
        builder.show()
    }

    private fun getIdUsuario() {
        val preferencesId: SharedPreferences = getSharedPreferences("id_user", Context.MODE_PRIVATE)
        val getIdActiveUser = preferencesId.getString("id_user", "")

        retro.getUserById(getIdActiveUser!!.toInt())
            .enqueue(object : Callback<Usuario> {
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        nome_escrito.text = response.body()?.nomeUsuario.toString()
                        email_escrito.text = response.body()?.emailUsuario.toString()
                        tvFirstLetterName.text = response.body()?.nomeUsuario?.substring(0,1).toString()
                        getProjetos()
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

    fun getProjetos(){
        val preferencesId: SharedPreferences = getSharedPreferences("id_user", Context.MODE_PRIVATE)
        val getIdActiveUser = preferencesId.getString("id_user", "")

        retrofitProjeto.getProjectsByUser(getIdActiveUser!!.toInt()).enqueue(object: Callback<List<Projeto>>{
            val projetosList = mutableListOf<Projeto>()
            override fun onResponse(call: Call<List<Projeto>>, response: Response<List<Projeto>>) {
                if(response.body() != null){
                    response.body()?.forEach{ projeto ->
                        val projetoView = Projeto(
                            idProjeto = projeto.idProjeto,
                            tituloProjeto = projeto.tituloProjeto,
                            imagemProjeto = projeto.imagemProjeto,
                            descricaoProjeto = projeto.descricaoProjeto,
                            nomeUsuario = projeto.nomeUsuario,
                            primeiraLetraNome = projeto.primeiraLetraNome,
                            dataHoraProjeto = projeto.dataHoraProjeto,
                            totalVisualizacoesProjeto = projeto.totalVisualizacoesProjeto,
                            totalCurtidasProjeto = projeto.totalCurtidasProjeto,
                            fkUsuario = projeto.fkUsuario
                        )
                        projetosList.add(projetoView)
                        tvCountMyProjects.text = projetosList.size.toString()
                    }
                    rvProjects.layoutManager = GridLayoutManager(baseContext, 3)
                    rvProjects.adapter = ProjetoUserAdapter(projetosList) {
                        val intent = Intent(baseContext, ProjectsFull::class.java)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(baseContext, "Nao temos projetos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Projeto>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}