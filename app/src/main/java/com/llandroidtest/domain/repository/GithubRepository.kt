package com.llandroidtest.domain.repository

import com.llandroidtest.domain.model.PullRequestResponseItem
import com.llandroidtest.domain.model.RepositoryResponseItem
import com.llandroidtest.domain.model.toPullRequestResponseItem
import com.llandroidtest.domain.model.toRepositoryResponseItem
import com.llandroidtest.domain.service.GithubService
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubService: GithubService
) {
    companion object {
        const val exception = "Falha ao obter os dados"
    }

    suspend fun getRepositories(query: String, page: Int): List<RepositoryResponseItem> {
        return githubService.getRepositories(query, page)?.let {
            it.items.map { repo -> repo.toRepositoryResponseItem() }
        } ?: throw Exception(exception)
    }
    suspend fun getPullRequests(owner: String, repo: String): List<PullRequestResponseItem> {
        return githubService.getPullRequests(owner, repo)?.let {
            it.map { pr -> pr.toPullRequestResponseItem() }
        } ?: throw Exception(exception)
    }
}