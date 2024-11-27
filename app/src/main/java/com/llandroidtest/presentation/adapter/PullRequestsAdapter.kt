package com.llandroidtest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.llandroidtest.R
import com.llandroidtest.domain.model.PullRequestModel

class PullRequestsAdapter(private val pullRequestModels: List<PullRequestModel>) : RecyclerView.Adapter<PullRequestsAdapter.PullRequestViewHolder>() {

    fun submitList(list: List<PullRequestModel>) {
        (pullRequestModels as MutableList).clear()
        pullRequestModels.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pull_request, parent, false)
        return PullRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = pullRequestModels[position]
        holder.bind(pullRequest)
    }

    override fun getItemCount(): Int = pullRequestModels.size

    class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_pr_title)
        private val bodyTextView: TextView = itemView.findViewById(R.id.tv_pr_body)
        private val usernameTextView: TextView = itemView.findViewById(R.id.tv_user_username)
        private val fullNameTextView: TextView = itemView.findViewById(R.id.tv_user_name)
        private val avatarImageView: ImageView = itemView.findViewById(R.id.iv_user_avatar)

        fun bind(pullRequestModel: PullRequestModel) {
            titleTextView.text = pullRequestModel.title
            bodyTextView.text = pullRequestModel.description
            usernameTextView.text = pullRequestModel.username
            fullNameTextView.text = pullRequestModel.fullName

            avatarImageView.setImageResource(R.drawable.ic_user)
        }
    }
}
