package com.llandroidtest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.llandroidtest.R
import com.llandroidtest.data.model.RepositoryResponse

class UserRepositoryAdapter(
    private val repositories: List<RepositoryResponse>,
    private val onItemClick: (RepositoryResponse) -> Unit
) : RecyclerView.Adapter<UserRepositoryAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgUserPhoto: ImageView = itemView.findViewById(R.id.imgUserPhoto)
        private val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvRepositoryName: TextView = itemView.findViewById(R.id.tvRepositoryName)
        private val tvRepositoryDescription: TextView = itemView.findViewById(R.id.tvRepositoryDescription)
        private val tvForksCount: TextView = itemView.findViewById(R.id.tvForksCount)
        private val tvLikesCount: TextView = itemView.findViewById(R.id.tvLikesCount)

        fun bind(repository: RepositoryResponse) {
//            Glide.with(itemView.context).load(repository.userPhotoUrl).into(imgUserPhoto)
//            tvUserName.text = repository.userName
//            tvName.text = repository.name
//            tvRepositoryName.text = repository.repositoryName
//            tvRepositoryDescription.text = repository.repositoryDescription
//            tvForksCount.text = repository.forksCount.toString()
//            tvLikesCount.text = repository.likesCount.toString()

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
}

