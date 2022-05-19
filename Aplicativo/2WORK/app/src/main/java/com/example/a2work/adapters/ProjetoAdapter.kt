package com.example.a2work.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a2work.R
import com.example.a2work.data.profile.models.Project

class ProjetoAdapter(val projetos: List<Project>) :
    RecyclerView.Adapter<ProjetoAdapter.ProjetoCardHolder>() {

    inner class ProjetoCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(projeto: Project) {
            itemView.findViewById<TextView>(R.id.tvTitle).text = projeto.title
            val imgViewContainer: ImageView = itemView.findViewById(R.id.iv_project)
            Glide.with(itemView).load(projeto.image).into(imgViewContainer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjetoCardHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_my_projects, parent, false)
        return ProjetoCardHolder(view)
    }

    override fun onBindViewHolder(holder: ProjetoCardHolder, position: Int) {
        holder.bind(projetos[position])
    }
    override fun getItemCount(): Int = projetos.size
}