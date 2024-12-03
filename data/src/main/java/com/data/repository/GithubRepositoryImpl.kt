package com.data.repository

import com.data.model.Owner
import com.data.model.PullRequestResponse
import com.domain.repository.GithubRepository
import com.domain.model.PullRequest
import com.data.model.RepositoryResponse
import com.data.remote.GithubApi
import com.data.model.Repository
import com.data.model.User
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository {

    override suspend fun getRepositories(query: String, page: Int): RepositoryResponse {
        val response = githubApi.getRepositories(query, page = page)

        val items = response.items.map {
            Repository(
                id = it.id,
                name = it.name,
                description = it.description,
                owner = Owner(
                    login = it.owner.login,
                    avatarUrl = it.owner.avatarUrl
                ),
                stargazersCount = it.stargazersCount,
                forksCount = it.forksCount
            )
        }

        return RepositoryResponse(
            totalCount = response.totalCount,
            incompleteResults = response.incompleteResults,
            items = items
        )
    }

    override suspend fun getPullRequests(owner: String, repo: String): List<PullRequest> {
        val pullRequests = githubApi.getPullRequests(owner, repo)

        if (pullRequests.isEmpty()) {
            return emptyList()
        }

        return pullRequests.map {
            PullRequestResponse(
                title = it.title,
                body = it.body,
                htmlUrl = it.htmlUrl,
                user = User(it.user.login, it.user.avatarUrl),
                createdAt = it.createdAt,
                state = it.state
            )
        }
    }


    override suspend fun getPullRequestsClosed(owner: String, repo: String): List<PullRequest> {
        val pullRequests = githubApi.getPullRequestsClosed(owner, repo)

        if (pullRequests.isEmpty()) {
            return emptyList()
        }

        return pullRequests.map {
            PullRequestResponse(
                title = it.title,
                body = it.body,
                htmlUrl = it.htmlUrl,
                user = User(it.user.login, it.user.avatarUrl),
                createdAt = it.createdAt,
                state = it.state
            )
        }
    }
}