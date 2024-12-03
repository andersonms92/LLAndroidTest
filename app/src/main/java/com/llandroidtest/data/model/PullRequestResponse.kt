package com.llandroidtest.data.model

import com.google.gson.annotations.SerializedName

data class PullRequestResponse(
    val title: String,
    val body: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val user: User,
    @SerializedName("created_at")
    val createdAt: String,
    val state: String
)

data class User(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)