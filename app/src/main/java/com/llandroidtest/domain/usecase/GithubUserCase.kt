package com.llandroidtest.domain.usecase

import com.llandroidtest.domain.model.PullRequestResponseItem
import com.llandroidtest.domain.model.RepositoryResponseItem
import com.llandroidtest.domain.repository.GithubRepository
import javax.inject.Inject

class GithubUseCase @Inject constructor(private val githubRepository: GithubRepository) {

    suspend fun getRepositories(query: String, page: Int): List<RepositoryResponseItem> {
        return try {
            githubRepository.getRepositories(query, page)
        } catch (e: Exception) {
            println("Erro: ${e.message}")
            throw e
        }
    }

    suspend fun getPullRequests(owner: String, repo: String): List<PullRequestResponseItem> {
        return try {
            githubRepository.getPullRequests(owner, repo)
        } catch (e: Exception) {
            println("Erro: ${e.message}")
            throw e
        }
    }
}