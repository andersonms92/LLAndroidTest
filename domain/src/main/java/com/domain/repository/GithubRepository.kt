package com.domain.repository

import com.domain.model.PullRequest
import com.domain.model.RepositoryResponse

interface GithubRepository {
    suspend fun getRepositories(query: String, page: Int): RepositoryResponse
    suspend fun getPullRequests(owner: String, repo: String): List<PullRequest>
    suspend fun getPullRequestsClosed(owner: String, repo: String): List<PullRequest>
}