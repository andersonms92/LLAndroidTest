package com.domain.usecase

import com.domain.model.RepositoryResponse
import com.domain.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {

    suspend operator fun invoke(query: String, page: Int): Resource<RepositoryResponse> {
        return try {
            val response = withContext(Dispatchers.IO) {
                githubRepository.getRepositories(query, page)
            }
            
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}