package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a2work.adapters.ProjetoFeedAdapter
import com.example.a2work.data.profile.models.Projeto
import com.example.a2work.databinding.ActivityFeedBinding
import com.example.a2work.databinding.ActivityProjetosBinding
import com.example.a2work.profile.SlideActivity
import com.example.a2work.rest.Rest
import com.example.a2work.services.ProjectService
import kotlinx.android.synthetic.main.activity_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedActivity : AppCompatActivity() {
    private val retrofitProjeto = Rest.getInstance().create(ProjectService::class.java)
    private lateinit var binding: ActivityFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getProjetos()

//        bottom_navigation_home.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navi_home -> {
//                }
//                R.id.navi_upload -> {
//                    startActivity(Intent(this, UploadActivity::class.java))
//                }
//                R.id.navi_projeto -> {
//                    startActivity(Intent(this, ProjetosActivity::class.java))
//                }
//                R.id.navi_conta -> {
//                    startActivity(Intent(this, ProfileActivity::class.java))
//                }
//                R.id.navi_planos -> {
//                    startActivity(Intent(this, SlideActivity::class.java))
//                }
//            }
//            true
//        }

    }

    fun getProjetos(){
        retrofitProjeto.getProjetosRecentes().enqueue(object: Callback<List<Projeto>> {
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
                            totalVisualizacoesProjeto = projeto.totalVisualizacoesProjeto,
                            totalCurtidasProjeto = projeto.totalCurtidasProjeto,
//                            primeiraPergunta = projeto.primeiraPergunta,
//                            segundaPergunta = projeto.segundaPergunta,
//                            terceiraPergunta = projeto.terceiraPergunta,
                            fkUsuario = projeto.fkUsuario
                        )
                        projetosList.add(ProjetoView)
                    }
                    binding.recyclerViewProjetosContainer.layoutManager = GridLayoutManager(baseContext, 1)
                    binding.recyclerViewProjetosContainer.adapter = ProjetoFeedAdapter(projetosList) {
                        val intent = Intent(baseContext, ProjectProfile::class.java)
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



    fun upload(view: View) {
        startActivity(Intent(baseContext, UploadActivity::class.java))
    }

    fun perfilProjeto(view: View) {
        startActivity(Intent(baseContext, ProjectProfile::class.java))
    }

    fun projeto(view: View) {
        startActivity(Intent(baseContext, ProjectsFull::class.java))
    }
}