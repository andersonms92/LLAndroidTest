package com.llandroidtest.domain.usecase

import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.domain.repository.GithubRepository
import com.llandroidtest.presentation.viewmodel.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPullRequestsUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {

    suspend operator fun invoke(owner: String, repo: String): Resource<List<PullRequestResponse>> {
        return try {
            val response = withContext(Dispatchers.IO) {
                githubRepository.getPullRequests(owner, repo)
            }
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}
