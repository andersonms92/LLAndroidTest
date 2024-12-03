package com.llandroidtest.domain.repository

import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.data.model.RepositoryResponse

interface GithubRepository {
    suspend fun getRepositories(query: String, page: Int): RepositoryResponse
    suspend fun getPullRequests(owner: String, repo: String): List<PullRequestResponse>
    suspend fun getPullRequestsClosed(owner: String, repo: String): List<PullRequestResponse>
}