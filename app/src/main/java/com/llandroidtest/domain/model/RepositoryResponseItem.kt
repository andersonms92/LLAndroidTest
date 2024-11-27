package com.llandroidtest.domain.model

import com.llandroidtest.data.model.Owner
import com.llandroidtest.data.model.Repository
import com.llandroidtest.data.model.RepositoryResponse

data class RepositoryResponseItem(
    val name: String,
    val owner: OwnerItem,
    val description: String?,
    val stargazersCount: Int,
    val forksCount: Int,
    val createdAt: String
)

data class OwnerItem(
    val name: String,
    val avatarUrl: String
)

data class RepositoryResponseData(
    val data: List<RepositoryResponseItem>,
    val meta: MetaItem
)

fun Owner.toOwnerItem(): OwnerItem {
    return OwnerItem(
        name = this.login,
        avatarUrl = this.avatar_url
    )
}

fun Repository.toRepositoryResponseItem(): RepositoryResponseItem {
    return RepositoryResponseItem(
        name = this.name,
        owner = this.owner.toOwnerItem(),
        description = this.description,
        stargazersCount = this.stargazers_count,
        forksCount = this.forks_count,
        createdAt = ""
    )
}

fun RepositoryResponse.toRepositoryResponseItemData(): RepositoryResponseData {
    return RepositoryResponseData(
        data = this.items.map { it.toRepositoryResponseItem() },
        meta = MetaItem(
            page = 1,
            pages = 1,
            page_size = 10,
            records = this.total_count
        )
    )
}