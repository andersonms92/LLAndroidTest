package com.llandroidtest.presentation.viewmodel

import com.llandroidtest.data.model.Repository
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.utils.MainCoroutineRule
import com.llandroidtest.data.model.RepositoryResponse
import com.llandroidtest.domain.repository.GithubRepository
import com.llandroidtest.domain.usecase.GetClosedPullRequestsUseCase
import com.llandroidtest.domain.usecase.GetPullRequestsUseCase
import com.llandroidtest.domain.usecase.GetRepositoriesUseCase
import com.llandroidtest.utils.MocksRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SharedViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SharedViewModel
    private val getClosedPullRequestsUseCase: GetClosedPullRequestsUseCase = mockk()
    private val getPullRequestsUseCase: GetPullRequestsUseCase = mockk()
    private val getRepositoriesUseCase: GetRepositoriesUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = SharedViewModel(
            getClosedPullRequestsUseCase = getClosedPullRequestsUseCase,
            getPullRequestsUseCase = getPullRequestsUseCase,
            getRepositoriesUseCase = getRepositoriesUseCase
        )
    }

    @Test
    fun `getRepositories emits success when repository call is successful`() = runTest {
        // Given
        val query = "language:java"
        val page = 1
        val repositories =
            MocksRepository.mockRepositories

        val response = Resource.Success(RepositoryResponse(2, items = repositories, incompleteResults = false))

        coEvery { getRepositoriesUseCase(query, page) } returns response

        // When
        viewModel.getRepositories(query, page)

        // Then
        val emittedValues = mutableListOf<Resource<List<Repository>>>()
        val observer = Observer<Resource<List<Repository>>> { emittedValues.add(it) }
        viewModel.repositories.observeForever(observer)

        advanceUntilIdle()

        assert((emittedValues.last() as Resource.Success).data == repositories)

        viewModel.repositories.removeObserver(observer)
    }


    @Test
    fun `getPullRequests emits success when pull request call is successful`() = runTest {
        // Given
        val owner = "owner"
        val repo = "repo"
        val pullRequests = MocksRepository.mockPullRequests

        val response = Resource.Success(pullRequests)

        coEvery { getPullRequestsUseCase(owner, repo) } returns response

        // When
        viewModel.getPullRequests(owner, repo)

        // Then
        val emittedValues = mutableListOf<Resource<List<PullRequestResponse>>>()
        val observer = Observer<Resource<List<PullRequestResponse>>> { emittedValues.add(it) }
        viewModel.pullRequests.observeForever(observer)

        advanceUntilIdle()

        assert((emittedValues.last() as Resource.Success).data == pullRequests)

        viewModel.pullRequests.removeObserver(observer)
    }

    @Test
    fun `getPullRequests emits error when pull request call fails`() = runTest {
        // Given
        val owner = "owner"
        val repo = "repo"
        val errorMessage = "Erro ao obter pull requests"

        val response = Resource.Error<List<PullRequestResponse>>(errorMessage)

        coEvery { getPullRequestsUseCase(owner, repo) } returns response

        // When
        viewModel.getPullRequests(owner, repo)

        // Then
        val emittedValues = mutableListOf<Resource<List<PullRequestResponse>>>()
        val observer = Observer<Resource<List<PullRequestResponse>>> { emittedValues.add(it) }
        viewModel.pullRequests.observeForever(observer)

        advanceUntilIdle()

        assert((emittedValues.last() as Resource.Error).message == errorMessage)

        viewModel.pullRequests.removeObserver(observer)
    }

    @Test
    fun `getPullRequestsClosed emits success when closed pull request call is successful`() = runTest {
        // Given
        val owner = "owner"
        val repo = "repo"
        val closedPullRequests = MocksRepository.mockPullRequests

        val response = Resource.Success(closedPullRequests)

        coEvery { getClosedPullRequestsUseCase(owner, repo) } returns response

        // When
        viewModel.getPullRequestsClosed(owner, repo)

        // Then
        val emittedValues = mutableListOf<Resource<List<PullRequestResponse>>>()
        val observer = Observer<Resource<List<PullRequestResponse>>> { emittedValues.add(it) }
        viewModel.pullRequestsClosed.observeForever(observer)

        advanceUntilIdle()

        assert((emittedValues.last() as Resource.Success).data == closedPullRequests)

        viewModel.pullRequestsClosed.removeObserver(observer)
    }

    @Test
    fun `getPullRequestsClosed emits error when closed pull request call fails`() = runTest {
        // Given
        val owner = "owner"
        val repo = "repo"
        val errorMessage = "Erro ao obter pull requests fechados"

        val response = Resource.Error<List<PullRequestResponse>>(errorMessage)

        coEvery { getClosedPullRequestsUseCase(owner, repo) } returns response

        // When
        viewModel.getPullRequestsClosed(owner, repo)

        // Then
        val emittedValues = mutableListOf<Resource<List<PullRequestResponse>>>()
        val observer = Observer<Resource<List<PullRequestResponse>>> { emittedValues.add(it) }
        viewModel.pullRequestsClosed.observeForever(observer)

        advanceUntilIdle()

        assert((emittedValues.last() as Resource.Error).message == errorMessage)

        viewModel.pullRequestsClosed.removeObserver(observer)
    }


}