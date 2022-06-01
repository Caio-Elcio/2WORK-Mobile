package com.example.a2work

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a2work.adapters.ProjetoAdapter
import com.example.a2work.data.profile.models.Project
import com.example.a2work.data.profile.models.Projeto
import com.example.a2work.databinding.ActivityProjetosBinding
import com.example.a2work.profile.SlideActivity
import com.example.a2work.rest.Rest
import com.example.a2work.services.ProjectService
import kotlinx.android.synthetic.main.activity_projetos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjetosActivity : AppCompatActivity() {

    private val retrofitProjeto = Rest.getInstance().create(ProjectService::class.java)
    private lateinit var binding: ActivityProjetosBinding
    private lateinit var botaoCurtida: LinearLayout
    var contadorCurtida = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjetosBinding.inflate(layoutInflater)

        setContentView(binding.root)
        getProjetos()
    }



    fun getProjetos(){
        retrofitProjeto.getProjetos().enqueue(object: Callback<List<Projeto>>{
            val projetosList = mutableListOf<Projeto>()
            override fun onResponse(call: Call<List<Projeto>>, response: Response<List<Projeto>>) {
                if(response.body() != null){
                    response.body()?.forEach{ projeto ->
                        val ProjetoView = Projeto(
                            idProjeto = projeto.idProjeto,
                            tituloProjeto = projeto.tituloProjeto,
                            imagemProjeto = projeto.imagemProjeto,
                            descricaoProjeto = projeto.descricaoProjeto,
                            nomeUsuario = projeto.nomeUsuario,
                            primeiraLetraNome = projeto.primeiraLetraNome,
                            dataHoraProjeto = projeto.dataHoraProjeto,
                            totalVisualizacoesProjeto = null,
                            totalCurtidasProjeto = null,
//                            primeiraPergunta = false,
//                            segundaPergunta = false,
//                            terceiraPergunta = false,
                            fkUsuario = null

                        )
                        projetosList.add(ProjetoView)

                    }
                    binding.recyclerViewProjetosContainer.layoutManager = GridLayoutManager(baseContext, 3)
                    binding.recyclerViewProjetosContainer.adapter = ProjetoAdapter(projetosList) {
                        val preferences: SharedPreferences = getSharedPreferences("id_project", Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = preferences.edit()
                        it.idProjeto?.let { it1 ->
                            editor.putInt("id_project", it1)
                        }
                        editor.apply()
                        val intent = Intent(baseContext, ProjectsFull::class.java)
                        intent.putExtra("ID_PROJECT_SELECTED", it.idProjeto)
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