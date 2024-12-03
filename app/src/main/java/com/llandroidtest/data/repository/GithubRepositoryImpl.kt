package com.llandroidtest.data.repository

import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.data.model.RepositoryResponse
import com.llandroidtest.data.remote.GithubApi
import com.llandroidtest.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository {

    override suspend fun getRepositories(query: String, page: Int): RepositoryResponse {
        return githubApi.getRepositories(query, page = page)
    }

    override suspend fun getPullRequests(owner: String, repo: String): List<PullRequestResponse> {
        return githubApi.getPullRequests(owner, repo)
    }

    override suspend fun getPullRequestsClosed(owner: String, repo: String): List<PullRequestResponse> {
        return githubApi.getPullRequestsClosed(owner, repo)
    }
}