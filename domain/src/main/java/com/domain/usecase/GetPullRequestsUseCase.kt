package com.domain.usecase

import com.domain.model.PullRequest
import com.domain.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPullRequestsUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {

    suspend operator fun invoke(owner: String, repo: String): Resource<List<PullRequest>> {
        return try {
            val response = withContext(Dispatchers.IO) {
                githubRepository.getPullRequests(owner, repo)
            }
            if (response.isEmpty()) {
                Resource.Error("No pull requests found")
            } else {
                Resource.Success(response)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}
