package com.llandroidtest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.data.model.Repository
import com.llandroidtest.domain.usecase.GetClosedPullRequestsUseCase
import com.llandroidtest.domain.usecase.GetPullRequestsUseCase
import com.llandroidtest.domain.usecase.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val getPullRequestsUseCase: GetPullRequestsUseCase,
    private val getClosedPullRequestsUseCase: GetClosedPullRequestsUseCase
) : ViewModel() {

    private val _repositories = MutableLiveData<Resource<List<Repository>>>()
    val repositories: LiveData<Resource<List<Repository>>> = _repositories

    private val _pullRequests = MutableLiveData<Resource<List<PullRequestResponse>>>()
    val pullRequests: LiveData<Resource<List<PullRequestResponse>>> = _pullRequests

    private val _pullRequestsClosed = MutableLiveData<Resource<List<PullRequestResponse>>>()
    val pullRequestsClosed: LiveData<Resource<List<PullRequestResponse>>> = _pullRequestsClosed

    var currentPage =  1
    var isLoading = false
    var allRepositories =  mutableListOf<Repository>()

    fun getRepositories(query: String, page: Int = currentPage) {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            _repositories.postValue(Resource.Loading())
            try {
                when (val response = getRepositoriesUseCase(query, page)) {
                    is Resource.Success -> {
                        if (page == 1) {
                            allRepositories.clear()
                        }
                        allRepositories.addAll(response.data.items)
                        _repositories.postValue(Resource.Success(allRepositories))
                        currentPage++
                    }
                    is Resource.Error -> {
                        _repositories.postValue(Resource.Error(response.message))
                    }

                    is Resource.Loading -> {}
                }
            } catch (e: Exception) {
                val errorMessage = getErrorMessage(e)
                _repositories.postValue(Resource.Error(errorMessage))
            } finally {
                isLoading = false
            }
        }
    }

    fun getPullRequests(owner: String, repo: String) {
        viewModelScope.launch {
            _pullRequests.postValue(Resource.Loading())
            try {
                when (val response = getPullRequestsUseCase(owner, repo)) {
                    is Resource.Success -> {
                        _pullRequests.postValue(Resource.Success(response.data))
                    }
                    is Resource.Error -> {
                        _pullRequests.postValue(Resource.Error(response.message))
                    }

                    is Resource.Loading -> {}
                }
            } catch (e: Exception) {
                val errorMessage = getErrorMessage(e)
                _pullRequests.postValue(Resource.Error(errorMessage))
            }
        }
    }

    fun getPullRequestsClosed(owner: String, repo: String) {
        viewModelScope.launch {
            _pullRequestsClosed.postValue(Resource.Loading())
            try {
                when (val response = getClosedPullRequestsUseCase(owner, repo)) {
                    is Resource.Success -> {
                        _pullRequestsClosed.postValue(Resource.Success(response.data))
                    }
                    is Resource.Error -> {
                        _pullRequestsClosed.postValue(Resource.Error(response.message))
                    }

                    is Resource.Loading -> {}
                }
            } catch (e: Exception) {
                val errorMessage = getErrorMessage(e)
                _pullRequestsClosed.postValue(Resource.Error(errorMessage))
            }
        }
    }

    fun resetPagination() {
        currentPage = 1
        allRepositories.clear()
    }

    private fun getErrorMessage(e: Exception): String {
        return when (e) {
            is java.net.UnknownHostException -> "Sem conexão com a internet."
            is java.net.SocketTimeoutException -> "A solicitação expirou. Tente novamente."
            else -> e.message ?: "Erro desconhecido"
        }
    }
}
