package com.llandroidtest.data.model

data class PullRequestResponse(
    val title: String,
    val body: String,
    val author: Author,
    val createdAt: String
)

data class Author(
    val name: String,
    val photoUrl: String
)