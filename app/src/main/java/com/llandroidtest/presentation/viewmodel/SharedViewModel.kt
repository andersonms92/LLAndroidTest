package com.llandroidtest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.data.model.RepositoryResponse
import com.llandroidtest.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _repositories = MutableLiveData<Resource<RepositoryResponse>>()
    val repositories: LiveData<Resource<RepositoryResponse>> = _repositories

    private val _pullRequests = MutableLiveData<Resource<List<PullRequestResponse>>>()
    val pullRequests: LiveData<Resource<List<PullRequestResponse>>> = _pullRequests

    fun getRepositories(query: String, page: Int) {
        viewModelScope.launch {
            _repositories.postValue(Resource.Loading())
            try {
                val response = githubRepository.getRepositories(query, page)
                _repositories.postValue(Resource.Success(response))
            } catch (e: Exception) {
                _repositories.postValue(Resource.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun getPullRequests(owner: String, repo: String) {
        viewModelScope.launch {
            _pullRequests.postValue(Resource.Loading())
            try {
                val response = githubRepository.getPullRequests(owner, repo)
                _pullRequests.postValue(Resource.Success(response))
            } catch (e: Exception) {
                _pullRequests.postValue(Resource.Error(e.message ?: "Unknown error"))
            }
        }
    }
}

//@HiltViewModel
//class SharedViewModel @Inject constructor(
//    private val githubUseCase: GithubUseCase
//) : ViewModel() {
//
//    private val _repositories = MutableLiveData<Resource<RepositoryResponseData>>()
//    val repositories: LiveData<Resource<RepositoryResponseData>> = _repositories
//
//    private val _pullRequests = MutableLiveData<Resource<List<PullRequestResponse>>>()
//    val pullRequests: LiveData<Resource<List<PullRequestResponse>>> = _pullRequests
//
//    @SuppressLint("CheckResult")
//    suspend fun getRepositories(query: String, page: Int) {
//        _repositories.postValue(Resource.Loading())
//        githubUseCase.getRepositories(query, page)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ response ->
//                _repositories.postValue(Resource.Success(response))
//            }, { error ->
//                _repositories.postValue(Resource.Error(error.message ?: "Unknown error"))
//            })
//    }
//
//    @SuppressLint("CheckResult")
//    suspend fun getPullRequests(owner: String, repo: String) {
//        _pullRequests.postValue(Resource.Loading())
//        githubUseCase.getPullRequests(owner, repo)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ response ->
//                _pullRequests.postValue(Resource.Success(response))
//            }, { error ->
//                _pullRequests.postValue(Resource.Error(error.message ?: "Unknown error"))
//            })
//    }
//}


//@HiltViewModel
//class SharedViewModel @Inject constructor(
//    private val githubUseCase: GithubUseCase
//) : ViewModel() {
//
//    private val _repositoryList = MutableStateFlow<List<RepositoryResponseItem>>(emptyList())
//    val repositoryList: StateFlow<List<RepositoryResponseItem>> get() = _repositoryList
//
//    private val _pullRequestList = MutableStateFlow<List<PullRequestResponseItem>>(emptyList())
//    val pullRequestList: StateFlow<List<PullRequestResponseItem>> get() = _pullRequestList
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: StateFlow<Boolean> get() = _isLoading
//
//    private var currentPage = 1
//
//    fun getRepositories(query: String, page: Int) {
//        viewModelScope.launch {
//            if (_isLoading.value) return@launch
//            _isLoading.value = true
//            try {
//                val response = githubUseCase.getRepositories(query, page)
//                withContext(Dispatchers.Main) {
//                    _repositoryList.value = if (page == 1) {
//                        response
//                    } else {
//                        _repositoryList.value + response
//                    }
//                    currentPage = page
//                }
//            } catch (e: Exception) {
//                _isLoading.value = false
//                Timber.tag("Endpoint getRepositories").e(e)
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
//    fun getPullRequests(owner: String, repo: String) {
//        viewModelScope.launch {
//            if (_isLoading.value) return@launch
//            _isLoading.value = true
//            try {
//                val response = githubUseCase.getPullRequests(owner, repo)
//                withContext(Dispatchers.Main) {
//                    _pullRequestList.value = response
//                }
//            } catch (e: Exception) {
//                _isLoading.value = false
//                Timber.tag("Endpoint getPullRequests").e(e)
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
//    fun loadMoreRepositories(query: String) {
//        if (!_isLoading.value) {
//            getRepositories(query, currentPage + 1)
//        }
//    }
//}
