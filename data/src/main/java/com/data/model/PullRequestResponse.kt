package com.data.model

import com.domain.model.PullRequest
import com.google.gson.annotations.SerializedName

data class PullRequestResponse(
    override val title: String = "Título não disponível",
    override val body: String = "Corpo não disponível",
    @SerializedName("html_url") override val htmlUrl: String = "URL não disponível",
    override val user: User = User("Desconhecido", "https://default-avatar.com"),
    @SerializedName("created_at") override val createdAt: String = "Data não disponível",
    override val state: String = "Estado não disponível"
) : PullRequest


data class User(
    override val login: String = "Login não disponível",
    @SerializedName("avatar_url") override val avatarUrl: String = "Avatar URL não disponível"
) : com.domain.model.User