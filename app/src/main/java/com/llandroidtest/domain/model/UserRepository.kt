package com.llandroidtest.domain.model

data class UserRepository(
    val userPhotoUrl: String,
    val userName: String,
    val name: String,
    val repositoryName: String,
    val repositoryDescription: String,
    val forksCount: Int,
    val likesCount: Int
)
