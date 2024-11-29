package com.llandroidtest.domain.repository

import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.data.model.RepositoryResponse
import com.llandroidtest.data.remote.GithubApi
import javax.inject.Inject

interface GithubRepository {
    suspend fun getRepositories(query: String, page: Int): RepositoryResponse
    suspend fun getPullRequests(owner: String, repo: String): List<PullRequestResponse>
}

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository {

    override suspend fun getRepositories(query: String, page: Int): RepositoryResponse {
        return githubApi.getRepositories(query, page = page)
    }

    override suspend fun getPullRequests(owner: String, repo: String): List<PullRequestResponse> {
        return githubApi.getPullRequests(owner, repo)
    }
}