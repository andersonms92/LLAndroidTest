package com.domain.model

interface PullRequest {
    val title: String
    val body: String
    val htmlUrl: String
    val user: User
    val createdAt: String
    val state: String
}

interface User {
    val login: String
    val avatarUrl: String
}