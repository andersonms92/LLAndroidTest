package com.llandroidtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llandroidtest.domain.model.PullRequestResponseItem
import com.llandroidtest.domain.model.RepositoryResponseItem
import com.llandroidtest.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
@Inject constructor(
    private val githubUseCase: GithubUseCase
) : ViewModel() {

    private val _repositoryList = MutableStateFlow<List<RepositoryResponseItem>>(emptyList())
    val repositoryList: StateFlow<List<RepositoryResponseItem>> get() = _repositoryList

    private val _pullRequestList = MutableStateFlow<List<PullRequestResponseItem>>(emptyList())
    val pullRequestList: StateFlow<List<PullRequestResponseItem>> get() = _pullRequestList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    suspend fun getRepositories(query: String, page: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = githubUseCase.getRepositories(query, page)
                withContext(Dispatchers.Main) {
                    _repositoryList.value = response
                }
            } catch (e: Exception) {
                _isLoading.value = false
                Timber.tag("Endpoint getRepositories").e(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun getPullRequests(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = githubUseCase.getPullRequests(owner, repo)
                withContext(Dispatchers.Main) {
                    _pullRequestList.value = response
                }
            } catch (e: Exception) {
                _isLoading.value = false
                Timber.tag("Endpoint getPullRequests").e(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}