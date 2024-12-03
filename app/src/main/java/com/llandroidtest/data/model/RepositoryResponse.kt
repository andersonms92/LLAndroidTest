package com.llandroidtest.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<Repository>
)

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val owner: Owner,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int
)

data class Owner(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)