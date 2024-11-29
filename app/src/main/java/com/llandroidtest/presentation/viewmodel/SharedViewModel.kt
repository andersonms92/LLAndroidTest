package com.llandroidtest.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.domain.model.PullRequestResponseItem
import com.llandroidtest.domain.model.RepositoryResponseData
import com.llandroidtest.domain.model.RepositoryResponseItem
import com.llandroidtest.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import com.llandroidtest.presentation.viewmodel.Resource


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val githubUseCase: GithubUseCase
) : ViewModel() {

    private val _repositories = MutableLiveData<Resource<RepositoryResponseData>>()
    val repositories: LiveData<Resource<RepositoryResponseData>> = _repositories

    private val _pullRequests = MutableLiveData<Resource<List<PullRequestResponse>>>()
    val pullRequests: LiveData<Resource<List<PullRequestResponse>>> = _pullRequests

    @SuppressLint("CheckResult")
    suspend fun getRepositories(query: String, page: Int) {
        _repositories.postValue(Resource.Loading())
        githubUseCase.getRepositories(query, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _repositories.postValue(Resource.Success(response))
            }, { error ->
                _repositories.postValue(Resource.Error(error.message ?: "Unknown error"))
            })
    }

    @SuppressLint("CheckResult")
    suspend fun getPullRequests(owner: String, repo: String) {
        _pullRequests.postValue(Resource.Loading())
        githubUseCase.getPullRequests(owner, repo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _pullRequests.postValue(Resource.Success(response))
            }, { error ->
                _pullRequests.postValue(Resource.Error(error.message ?: "Unknown error"))
            })
    }
}


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
