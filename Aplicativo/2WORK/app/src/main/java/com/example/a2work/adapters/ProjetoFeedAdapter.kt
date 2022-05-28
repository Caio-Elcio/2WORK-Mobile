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

class ProjetoFeedAdapter (
    val projetos: List<Projeto>,
    val onClick: (Projeto) -> Unit
) :RecyclerView.Adapter<ProjetoFeedAdapter.ViewHolder>() {

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(projeto: Projeto, onProjectClickListener: (Projeto) -> Unit) {
//            val imgViewContainer: ImageView = itemView.findViewById(R.id.iv_project)
//            Glide.with(itemView)
//                .load(imgViewContainer)
//                .apply(RequestOptions())
//                .into(imgViewContainer)

            itemView.findViewById<TextView>(R.id.tvDescription).text = projeto.descricaoProjeto
            itemView.findViewById<TextView>(R.id.tvUsuario).text = projeto.nomeUsuario
            itemView.findViewById<TextView>(R.id.tvUsuarioLetra).text = projeto.primeiraLetraNome
            itemView.findViewById<TextView>(R.id.tvDataHorario).text = projeto.dataHoraProjeto

            itemView.setOnClickListener {
                onProjectClickListener(projeto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_my_projects_recents, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val projeto = projetos[position]
        holder.bind(projeto, onClick)
    }
    override fun getItemCount(): Int = projetos.size
}