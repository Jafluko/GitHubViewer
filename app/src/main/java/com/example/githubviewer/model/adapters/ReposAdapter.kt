package com.example.githubviewer.model.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubviewer.databinding.ItemBinding
import com.example.githubviewer.model.Repo

class ReposAdapter(private val click: (Int, String) -> Unit) : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    private var reposList: List<Repo> = listOf()

    fun setList(list: List<Repo>) {
        reposList = list
    }

    inner class RepoViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        with(holder) {
            with(reposList[position]) {
                binding.apply {
                    repoName.text = name
                    repoProgLeng.text = language
                    repoDescription.text = description
                    layoutCard.setOnClickListener{ click(id!!, name!!) }
                }
            }
        }
    }

    override fun getItemCount(): Int = reposList.size
}