package com.data.model

import com.domain.model.PullRequest
import com.google.gson.annotations.SerializedName

data class PullRequestResponse(
   override val title: String,
   override val body: String,
   @SerializedName("html_url")
   override val htmlUrl: String,
   override val user: User,
   @SerializedName("created_at")
   override val createdAt: String,
   override val state: String
) : PullRequest

data class User(
    override val login: String,
    @SerializedName("avatar_url")
    override val avatarUrl: String
) : com.domain.model.User