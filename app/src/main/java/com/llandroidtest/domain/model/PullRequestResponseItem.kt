package com.llandroidtest.domain.model

import com.llandroidtest.data.model.Author
import com.llandroidtest.data.model.PullRequestResponse

data class PullRequestResponseItem(
    val title: String,
    val body: String,
    val author: AuthorItem,
    val createdAt: String
)

data class AuthorItem(
    val name: String,
    val photoUrl: String
)

data class PullRequestResponseData(
    val data: List<PullRequestResponseItem>,
    val meta: MetaItem
)

data class MetaItem(
    val page: Int,
    val pages: Int,
    val page_size: Int,
    val records: Int
)

fun Author.toAuthorItem(): AuthorItem {
    return AuthorItem(
        name = this.name,
        photoUrl = this.photoUrl
    )
}

fun PullRequestResponse.toPullRequestResponseItem(): PullRequestResponseItem {
    return PullRequestResponseItem(
        title = this.title,
        body = this.body,
        author = this.author.toAuthorItem(),
        createdAt = this.createdAt
    )
}

fun PullRequestResponseData.toPullRequestResponseItemData(): PullRequestResponseData {
    return PullRequestResponseData(
        data = this.data,
        meta = this.meta
    )
}
