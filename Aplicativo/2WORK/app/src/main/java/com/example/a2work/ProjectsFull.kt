package com.example.a2work

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import com.example.a2work.data.profile.models.Projeto
import com.example.a2work.rest.Rest
import com.example.a2work.services.ProjectService
import kotlinx.android.synthetic.main.activity_projects_full.*
import kotlinx.android.synthetic.main.activity_projects_full.nome_escrito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectsFull : AppCompatActivity() {

    private val retro = Rest.getInstance().create(ProjectService::class.java)
    private var idUserSelected: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects_full)
        verProjeto()

        layoutNameUser.setOnClickListener {
            val intent = Intent(this, ProjectProfile::class.java)
            intent.putExtra("ID_USER_SELECTED", idUserSelected)
            startActivity(intent)
        }
    }

    private fun verProjeto() {
        retro.verProjeto(1)
            .enqueue(object : Callback<Projeto> {
                override fun onResponse(call: Call<Projeto>, response: Response<Projeto>) {
                    if (response.isSuccessful) {
                        nome_projeto.text = response.body()?.tituloProjeto.toString()
                        descricao_projeto.text = response.body()?.descricaoProjeto.toString()
                        tvUsuarioLetra.text = response.body()?.nomeUsuario.toString()
                        nome_escrito.text = response.body()?.primeiraLetraNome.toString()
                        idUserSelected = response.body()?.fkUsuario

                        val imageBytes = Base64.decode(response.body()?.imagemProjeto, Base64.DEFAULT)
                        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                        val d: Drawable = BitmapDrawable(Bitmap.createBitmap(decodedImage))
                        imgProject.setBackgroundDrawable(d)

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