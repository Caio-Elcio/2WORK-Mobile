package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a2work.adapters.ProjetoAdapter
import com.example.a2work.data.profile.models.Projeto
import com.example.a2work.databinding.ActivityProjetosBinding
import com.example.a2work.rest.Rest
import com.example.a2work.services.ProjectService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjetosActivity : AppCompatActivity() {

    private val retrofitProjeto = Rest.getInstance().create(ProjectService::class.java)
    private lateinit var binding: ActivityProjetosBinding
    private lateinit var botaoCurtida: LinearLayout
    var contadorCurtida = 0
    private var listProjectsId: List<Int>? = null

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
                            fkUsuario = projeto.fkUsuario
                        )
                        projetosList.add(ProjetoView)

                        listProjectsId = response.body()?.map {
                            it.idProjeto!!
                        }
                    }
                    binding.recyclerViewProjetosContainer.layoutManager = GridLayoutManager(baseContext, 3)
                    binding.recyclerViewProjetosContainer.adapter = ProjetoAdapter(projetosList, listProjectsId) {

                        val intent = Intent(applicationContext, ProjectsFull::class.java)
                        intent.putExtra("ID_PROJECT_SELECTED", it)
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

    fun onCustomToggleClick(view: View) {
        val imagem = AppCompatResources.getDrawable(baseContext, R.drawable.ic_baseline_favorite_24)
        if(contadorCurtida == 1){
            contadorCurtida = 0
            val imagem2 = AppCompatResources.getDrawable(baseContext, R.drawable.ic_baseline_favorite_border_24)
            botaoCurtida.background = imagem2
        } else {
            contadorCurtida = 1
            Toast.makeText(this, "Obrigado pela curtida!", Toast.LENGTH_SHORT).show()
            botaoCurtida.background = imagem
        }
    }
}