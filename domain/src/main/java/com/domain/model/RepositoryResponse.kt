package com.domain.model

interface RepositoryResponse {
    val totalCount: Int
    val incompleteResults: Boolean
    val items: List<Repository>

}

interface Repository {
    val id: Long
    val name: String
    val description: String?
    val owner: Owner
    val stargazersCount: Int
    val forksCount: Int
}

interface Owner {
    val login: String
    val avatarUrl: String
}