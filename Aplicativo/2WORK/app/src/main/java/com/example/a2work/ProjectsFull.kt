package com.example.a2work

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.nome_escrito
import kotlinx.android.synthetic.main.activity_projects_full.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectsFull : AppCompatActivity() {

    private val retro = Rest.getInstance().create(ProjectService::class.java)

    var imageArray: ArrayList<Int> = ArrayList()
    var carouselView: CarouselView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects_full)
        verProjeto()

        imageArray.add(R.drawable.image_1)
        imageArray.add(R.drawable.image_2)
        imageArray.add(R.drawable.image_3)
        imageArray.add(R.drawable.image_4)

        carouselView = findViewById(R.id.carouselView)
        carouselView!!.pageCount = imageArray.size
        carouselView!!.setImageListener(imageListener)
    }

    var imageListener =
        ImageListener { position, imageView -> imageView.setImageResource(imageArray[position]) }

    private fun verProjeto() {
        val preferencesId: SharedPreferences = getSharedPreferences("id_user", Context.MODE_PRIVATE)
        val getIdActiveUser = preferencesId.getString("id_user", "")

        retro.verProjeto(getIdActiveUser!!.toInt())
            .enqueue(object : Callback<Projeto> {
                override fun onResponse(call: Call<Projeto>, response: Response<Projeto>) {
                    if (response.isSuccessful) {
                        nome_escrito.text = response.body()?.nomeUsuario.toString()
                        nome_projeto.text = response.body()?.tituloProjeto.toString()
                        descricao_projeto.text = response.body()?.descricaoProjeto.toString()

                    } else {
                        Toast.makeText(
                            baseContext,
                            response.message(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Projeto>, t: Throwable) {
                    Toast.makeText(
                        baseContext,
                        t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
    }
}