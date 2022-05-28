package com.example.a2work.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.a2work.R
import com.example.a2work.data.profile.models.Project
import com.example.a2work.data.profile.models.Projeto

class ProjetoAdapter (
    val projetos: List<Projeto>,
    val onClick: (Projeto) -> Unit
) :RecyclerView.Adapter<ProjetoAdapter.ViewHolder>() {

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
    }
    override fun getItemCount(): Int = projetos.size
}