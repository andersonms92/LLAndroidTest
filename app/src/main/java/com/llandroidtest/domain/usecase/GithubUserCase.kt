package com.llandroidtest.domain.usecase

import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.domain.model.PullRequestResponseItem
import com.llandroidtest.domain.model.RepositoryResponseData
import com.llandroidtest.domain.model.RepositoryResponseItem
import com.llandroidtest.domain.repository.GithubRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GithubUseCase {
    suspend fun getRepositories(query: String, page: Int): Single<RepositoryResponseData>
    suspend fun getPullRequests(owner: String, repo: String): Single<List<PullRequestResponse>>
}

class GithubUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository
) : GithubUseCase {

    override suspend fun getRepositories(query: String, page: Int): Single<RepositoryResponseData> {
        return githubRepository.getRepositories(query, page)
    }

    override suspend fun getPullRequests(owner: String, repo: String): Single<List<PullRequestResponse>> {
        return githubRepository.getPullRequests(owner, repo)
    }
}


//class GithubUseCase @Inject constructor(private val githubRepository: GithubRepository) {
//
//    suspend fun getRepositories(query: String, page: Int): List<RepositoryResponseItem> {
//        return try {
//            githubRepository.getRepositories(query, page)
//        } catch (e: Exception) {
//            println("Erro: ${e.message}")
//            throw e
//        }
//    }
//
//    suspend fun getPullRequests(owner: String, repo: String): List<PullRequestResponseItem> {
//        return try {
//            githubRepository.getPullRequests(owner, repo)
//        } catch (e: Exception) {
//            println("Erro: ${e.message}")
//            throw e
//        }
//    }
//}