package com.example.a2work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2work.adapters.ProjetoAdapter
import com.example.a2work.data.profile.models.Project
import com.example.a2work.databinding.ActivityProjetosBinding
import com.example.a2work.profile.SlideActivity
import kotlinx.android.synthetic.main.activity_projetos.*

class ProjetosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProjetosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjetosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val projetosList = listOf<Project>(
            Project(
                "Projeto 1",
                "https://alimentos.com.br/wp-content/uploads/2018/08/beneficios_da_maca_292140977.jpg"
            ),
            Project(
                "Projeto 2",
                "https://alimentos.com.br/wp-content/uploads/2018/08/beneficios_da_maca_292140977.jpg"
            ),
            Project(
                "Projeto 3",
                "https://alimentos.com.br/wp-content/uploads/2018/08/beneficios_da_maca_292140977.jpg"
            ),
            Project(
                "Projeto 4",
                "https://th.bing.com/th/id/OIP.LqJ6a9QonbNrjukRqpKbtQHaE8?pid=ImgDet&rs=1"
            )
        )

        binding.recyclerViewProjetosContainer.layoutManager = GridLayoutManager(baseContext, 2)
        binding.recyclerViewProjetosContainer.adapter = ProjetoAdapter(projetosList)

        bottom_navigation_projetos.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_home -> {
                    startActivity(Intent(this, FeedActivity::class.java))
                }
                R.id.navi_upload -> {
                    startActivity(Intent(this, UploadActivity::class.java))
                }
                R.id.navi_projeto -> {
                }
                R.id.navi_conta -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
                R.id.navi_planos -> {
                    startActivity(Intent(this, SlideActivity::class.java))
                }
            }
            true
        }
    }
}