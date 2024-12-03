package com.data.model.mapper

import com.domain.model.User

fun com.data.model.RepositoryResponse.toDomain(): com.domain.model.RepositoryResponse {
    return object : com.domain.model.RepositoryResponse {
        override val totalCount = this@toDomain.totalCount
        override val incompleteResults = this@toDomain.incompleteResults
        override val items = this@toDomain.items.map { it.toDomain() }
    }
}

fun com.data.model.Repository.toDomain(): com.domain.model.Repository {
    return object : com.domain.model.Repository {
        override val id = this@toDomain.id
        override val name = this@toDomain.name
        override val description = this@toDomain.description
        override val owner = this@toDomain.owner.toDomain()
        override val stargazersCount = this@toDomain.stargazersCount
        override val forksCount = this@toDomain.forksCount
    }
}

fun com.data.model.Owner.toDomain(): com.domain.model.Owner {
    return object : com.domain.model.Owner {
        override val login = this@toDomain.login
        override val avatarUrl = this@toDomain.avatarUrl
    }
}

fun com.data.model.PullRequestResponse.toDomain(): com.domain.model.PullRequest {
    return object : com.domain.model.PullRequest {
        override val title = this@toDomain.title
        override val body = this@toDomain.body
        override val htmlUrl = this@toDomain.htmlUrl
        override val user = this@toDomain.user
        override val createdAt = this@toDomain.createdAt
        override val state  = this@toDomain.state
    }
}

fun com.data.model.User.toDomain(): com.domain.model.User {
    return object : com.domain.model.User {
        override val login = this@toDomain.login
        override val avatarUrl = this@toDomain.avatarUrl
    }
}
