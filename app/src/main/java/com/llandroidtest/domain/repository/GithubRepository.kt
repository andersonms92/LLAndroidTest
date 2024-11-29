package com.llandroidtest.domain.repository

import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.domain.model.PullRequestResponseItem
import com.llandroidtest.domain.model.RepositoryResponseData
import com.llandroidtest.domain.model.RepositoryResponseItem
import com.llandroidtest.domain.model.toPullRequestResponseItem
import com.llandroidtest.domain.model.toRepositoryResponseItem
import com.llandroidtest.domain.service.GithubService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GithubRepository {
    suspend fun getRepositories(query: String, page: Int): Single<RepositoryResponseData>
    suspend fun getPullRequests(owner: String, repo: String): Single<List<PullRequestResponse>>
}

class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRepository {

    override suspend fun getRepositories(query: String, page: Int): Single<RepositoryResponseData> {
        return githubService.getRepositories(query, page)
    }

    override suspend fun getPullRequests(owner: String, repo: String): Single<List<PullRequestResponse>> {
        return githubService.getPullRequests(owner, repo)
    }
}

//class GithubRepository @Inject constructor(
//    private val githubService: GithubService
//) {
//    companion object {
//        const val exception = "Falha ao obter os dados"
//    }
//
//    suspend fun getRepositories(query: String, page: Int): List<RepositoryResponseItem> {
//        return githubService.getRepositories(query, page).let {
//            it.items.map { repo -> repo.toRepositoryResponseItem() }
//        }
//    }
//    suspend fun getPullRequests(owner: String, repo: String): List<PullRequestResponseItem> {
//        return githubService.getPullRequests(owner, repo).let {
//            it.map { pr -> pr.toPullRequestResponseItem() }
//        }
//    }
//}