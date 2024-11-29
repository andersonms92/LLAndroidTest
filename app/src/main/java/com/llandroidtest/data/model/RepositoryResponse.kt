package com.llandroidtest.data.model

data class RepositoryResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Repository>
)

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val owner: Owner,
    val stargazers_count: Int,
    val forks_count: Int
)

data class Owner(
    val login: String,
    val avatar_url: String
)