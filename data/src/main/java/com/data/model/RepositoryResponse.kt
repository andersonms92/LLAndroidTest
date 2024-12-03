package com.data.model

import com.domain.model.RepositoryResponse
import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("total_count")
    override val totalCount: Int,
    @SerializedName("incomplete_results")
    override val incompleteResults: Boolean,
    override val items: List<Repository>
) : RepositoryResponse

data class Repository(
    override val id: Long,
    override val name: String,
    override val description: String?,
    override val owner: Owner,
    @SerializedName("stargazers_count")
    override val stargazersCount: Int,
    @SerializedName("forks_count")
    override val forksCount: Int
) : com.domain.model.Repository

data class Owner(
    override val login: String,
    @SerializedName("avatar_url")
    override val avatarUrl: String
) : com.domain.model.Owner