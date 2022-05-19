package com.example.a2work.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2work.R
import com.example.a2work.data.profile.models.Project
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.layout_item_my_projects.view.*

class ProjectsListAdapter(
    private val projects: List<Project>,
    private val context: Context
) : RecyclerView.Adapter<ProjectsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.tv_my_projects
        val image = itemView.iv_project
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_item_my_projects,
            parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]
        holder?.let {
            it.title.text = project.title
        }
    }

    override fun getItemCount(): Int {
        return projects?.size ?: 0
    }
}