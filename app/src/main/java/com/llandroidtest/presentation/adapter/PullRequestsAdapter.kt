package com.llandroidtest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.llandroidtest.R
import com.llandroidtest.data.model.PullRequestResponse

class PullRequestsAdapter(
    private val pullRequests: MutableList<PullRequestResponse>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<PullRequestsAdapter.PullRequestViewHolder>() {

    fun submitList(list: List<PullRequestResponse>) {
        pullRequests.clear()
        pullRequests.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pull_request, parent, false)
        return PullRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = pullRequests[position]
        holder.bind(pullRequest)
        holder.itemView.setOnClickListener {
            onItemClick(pullRequest.htmlUrl)
        }
    }

    override fun getItemCount(): Int = pullRequests.size

    class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_pr_title)
        private val body: TextView = itemView.findViewById(R.id.tv_pr_body)
        private val avatar: ImageView = itemView.findViewById(R.id.iv_user_avatar)
        private val username: TextView = itemView.findViewById(R.id.tv_user_username)
        private val createdAt: TextView = itemView.findViewById(R.id.tv_user_name)

        fun bind(pullRequest: PullRequestResponse) {
            title.text = pullRequest.title
            body.text = pullRequest.body

            username.text = pullRequest.user.login
            Glide.with(itemView)
                .load(pullRequest.user.avatarUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .placeholder(R.drawable.ic_user)
                .into(avatar)

            createdAt.text = pullRequest.createdAt ?: "Unknown date"
        }
    }
}