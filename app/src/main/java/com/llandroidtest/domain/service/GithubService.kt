package com.llandroidtest.domain.service

import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.data.model.RepositoryResponse
import com.llandroidtest.data.remote.GithubApi
import retrofit2.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubService @Inject constructor(private val githubApi: GithubApi) {

    suspend fun getRepositories(query: String, page: Int): RepositoryResponse {
        return withContext(Dispatchers.IO) {
            try {
                val response = githubApi.getRepositories(query, page = page).blockingGet()
                return@withContext response
            } catch (e: HttpException) {
                throw Exception("Erro na chamada da API HTTP: ${e.message()}")
            } catch (e: Exception) {
                throw Exception("Erro na chamada da API: ${e.message}")
            }
        }
    }

    suspend fun getPullRequests(owner: String, repo: String): List<PullRequestResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = githubApi.getPullRequests(owner, repo).blockingGet()
                return@withContext response
            } catch (e: HttpException) {
                throw Exception("Erro na chamada da API HTTP: ${e.message()}")
            } catch (e: Exception) {
                throw Exception("Erro na chamada da API: ${e.message}")
            }
        }
    }
}