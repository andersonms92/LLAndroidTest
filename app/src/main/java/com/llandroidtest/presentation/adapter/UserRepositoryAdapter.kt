package com.llandroidtest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.llandroidtest.R
import com.llandroidtest.data.model.Repository

class UserRepositoryAdapter(
    private var repositories: List<Repository>,
    private val onItemClick: (Repository) -> Unit
) : RecyclerView.Adapter<UserRepositoryAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userPhoto: ImageView = itemView.findViewById(R.id.imgUserPhoto)
        private val userName: TextView = itemView.findViewById(R.id.tvUserName)
        private val repositoryName: TextView = itemView.findViewById(R.id.tvRepositoryName)
        private val repositoryDescription: TextView = itemView.findViewById(R.id.tvRepositoryDescription)
        private val forksCount: TextView = itemView.findViewById(R.id.tvForksCount)
        private val likesCount: TextView = itemView.findViewById(R.id.tvLikesCount)

        fun bind(repository: Repository) {
            userName.text = repository.owner.login
            repositoryName.text = repository.name
            repositoryDescription.text = repository.description ?: "No description available"
            forksCount.text = repository.forksCount.toString()
            likesCount.text = repository.stargazersCount.toString()

            Glide.with(itemView.context)
                .load(repository.owner.avatarUrl)
                .circleCrop()
                .into(userPhoto)

            itemView.setOnClickListener {
                onItemClick(repository)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount(): Int = repositories.size

    fun updateData(newRepositories: List<Repository>) {
        repositories = newRepositories
        notifyDataSetChanged()
    }
}
