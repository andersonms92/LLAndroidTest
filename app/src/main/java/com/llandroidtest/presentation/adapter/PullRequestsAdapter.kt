package com.llandroidtest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.llandroidtest.R
import com.llandroidtest.domain.model.PullRequest

class PullRequestsAdapter(private val pullRequests: List<PullRequest>) : RecyclerView.Adapter<PullRequestsAdapter.PullRequestViewHolder>() {

    fun submitList(list: List<PullRequest>) {
        (pullRequests as MutableList).clear()
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
    }

    override fun getItemCount(): Int = pullRequests.size

    class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_pr_title)
        private val bodyTextView: TextView = itemView.findViewById(R.id.tv_pr_body)
        private val usernameTextView: TextView = itemView.findViewById(R.id.tv_user_username)
        private val fullNameTextView: TextView = itemView.findViewById(R.id.tv_user_name)
        private val avatarImageView: ImageView = itemView.findViewById(R.id.iv_user_avatar)

        fun bind(pullRequest: PullRequest) {
            titleTextView.text = pullRequest.title
            bodyTextView.text = pullRequest.description
            usernameTextView.text = pullRequest.username
            fullNameTextView.text = pullRequest.fullName

            avatarImageView.setImageResource(R.drawable.ic_user)
        }
    }
}
