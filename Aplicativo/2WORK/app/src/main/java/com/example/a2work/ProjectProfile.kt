package com.example.a2work

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a2work.adapters.ProjetoUserAdapter
import com.example.a2work.data.profile.models.Projeto
import com.example.a2work.data.profile.models.Usuario
import com.example.a2work.rest.Rest
import com.example.a2work.services.ProjectService
import com.example.a2work.services.UsuarioService
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_project_profile.*
import kotlinx.android.synthetic.main.activity_project_profile.tvNameUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectProfile : AppCompatActivity() {

    private val retro = Rest.getInstance().create(UsuarioService::class.java)
    private val retrofitProjeto = Rest.getInstance().create(ProjectService::class.java)

    private var idUser: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_profile)

        idUser = intent.getIntExtra("ID_USER_SELECTED", 0)

        if (idUser != null) {
            getIdUsuario()
        }
    }


    private fun getIdUsuario() {
        idUser?.let {
            retro.getUserById(it)
                .enqueue(object : Callback<Usuario> {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                        if (response.isSuccessful) {
                            tvUsuarioLetra.text = response.body()?.nomeUsuario?.substring(0, 1)
                            tvNameUser.text = response.body()?.nomeUsuario?.split(" ")?.get(0)
                            tvNameComplete.text = "Nome completo: ${response.body()?.nomeUsuario}"
                            tvEmailUser.text = "Email: ${response.body()?.emailUsuario}"
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
    }

    fun getProjetos(){
        idUser?.let {
            retrofitProjeto.getProjectsByUser(it).enqueue(object: Callback<List<Projeto>>{
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
                        rvProjects.adapter = ProjetoUserAdapter(projetosList) {}
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
}