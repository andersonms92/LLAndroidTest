package com.llandroidtest.domain.model

data class UserRepositoryModel(
    val userPhotoUrl: String,
    val userName: String,
    val name: String,
    val repositoryName: String,
    val repositoryDescription: String,
    val forksCount: Int,
    val likesCount: Int
)
