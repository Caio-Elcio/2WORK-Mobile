package com.example.a2work.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.a2work.R
import com.example.a2work.data.profile.models.Projeto
import kotlinx.android.synthetic.main.layout_item_my_projects.view.*

class ProjetoUserAdapter (
    val projetos: List<Projeto>,
    val onClick: (Projeto) -> Unit
) :RecyclerView.Adapter<ProjetoUserAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.iv_project
        fun bind(projeto: Projeto, onProjectClickListener: (Projeto) -> Unit) {
            val imgViewContainer = R.drawable.capa
            Glide.with(itemView)
                .load(imgViewContainer)
                .apply(RequestOptions())
                .into(itemView.findViewById(R.id.iv_project))

            itemView.findViewById<TextView>(R.id.tvTitle).text = projeto.tituloProjeto

            itemView.setOnClickListener {
                onProjectClickListener(projeto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_my_projects, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val projeto = projetos[position]
        holder.bind(projeto, onClick)

        holder.let {
            val imageBytes = Base64.decode(projeto?.imagemProjeto, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            val d: Drawable = BitmapDrawable(Bitmap.createBitmap(decodedImage))
            it.image.setBackgroundDrawable(d)
        }
    }

    override fun getItemCount(): Int = projetos.size

}