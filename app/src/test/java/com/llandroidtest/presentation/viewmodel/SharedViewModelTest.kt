package com.llandroidtest.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import androidx.lifecycle.Observer
import com.llandroidtest.utils.MainCoroutineRule
import com.data.model.mapper.toDomain
import com.domain.model.PullRequest
import com.domain.usecase.Resource
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

    private lateinit var viewModel: com.presentation.viewmodel.SharedViewModel
    private val getClosedPullRequestsUseCase: com.domain.usecase.GetClosedPullRequestsUseCase = mockk()
    private val getPullRequestsUseCase: com.domain.usecase.GetPullRequestsUseCase = mockk()
    private val getRepositoriesUseCase: com.domain.usecase.GetRepositoriesUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = com.presentation.viewmodel.SharedViewModel(
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
        val repositories = MocksRepository.mockRepositories
        val dataResponse = com.data.model.RepositoryResponse(
            totalCount = 2,
            incompleteResults = false,
            items = repositories.map {
                com.data.model.Repository(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    owner = com.data.model.Owner(it.owner.login, it.owner.avatarUrl),
                    stargazersCount = it.stargazersCount,
                    forksCount = it.forksCount
                )
            }
        )
        val domainResponse = dataResponse.toDomain()

        coEvery { getRepositoriesUseCase(query, page) } returns Resource.Success(domainResponse)

        // When
        viewModel.getRepositories(query, page)

        // Then
        val emittedValues = mutableListOf<Resource<List<com.domain.model.Repository>>>()
        val observer = Observer<Resource<List<com.domain.model.Repository>>> { emittedValues.add(it) }
        viewModel.repositories.observeForever(observer)

        advanceUntilIdle()

        assert((emittedValues.last() as Resource.Success).data == domainResponse.items)

        viewModel.repositories.removeObserver(observer)
    }



    @Test
    fun `getPullRequests emits success when pull request call is successful`() = runTest {
        // Given
        val owner = "owner"
        val repo = "repo"
        val pullRequests = MocksRepository.mockPullRequests

        val mappedPullRequests = pullRequests.map { it.toDomain() }

        val response = Resource.Success(mappedPullRequests)

        coEvery { getPullRequestsUseCase(owner, repo) } returns response

        // When
        viewModel.getPullRequests(owner, repo)

        // Then
        val emittedValues = mutableListOf<Resource<List<PullRequest>>>()
        val observer = Observer<Resource<List<PullRequest>>> { emittedValues.add(it) }
        viewModel.pullRequests.observeForever(observer)

        advanceUntilIdle()

        assert((emittedValues.last() as Resource.Success).data == mappedPullRequests)

        viewModel.pullRequests.removeObserver(observer)
    }


    @Test
    fun `getPullRequests emits error when pull request call fails`() = runTest {
        // Given
        val owner = "owner"
        val repo = "repo"
        val errorMessage = "Erro ao obter pull requests"

        val response = Resource.Error<List<PullRequest>>(errorMessage)

        coEvery { getPullRequestsUseCase(owner, repo) } returns response

        // When
        viewModel.getPullRequests(owner, repo)

        // Then
        val emittedValues = mutableListOf<Resource<List<PullRequest>>>()
        val observer = Observer<Resource<List<PullRequest>>> { emittedValues.add(it) }
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

        val mappedClosedPullRequests = closedPullRequests.map { it.toDomain() }

        val response = Resource.Success(mappedClosedPullRequests)

        coEvery { getClosedPullRequestsUseCase(owner, repo) } returns response

        // When
        viewModel.getPullRequestsClosed(owner, repo)

        // Then
        val emittedValues = mutableListOf<Resource<List<PullRequest>>>()
        val observer = Observer<Resource<List<PullRequest>>> { emittedValues.add(it) }
        viewModel.pullRequestsClosed.observeForever(observer)

        advanceUntilIdle()

        assert((emittedValues.last() as Resource.Success).data == mappedClosedPullRequests)

        viewModel.pullRequestsClosed.removeObserver(observer)
    }

    @Test
    fun `getPullRequestsClosed emits error when closed pull request call fails`() = runTest {
        // Given
        val owner = "owner"
        val repo = "repo"
        val errorMessage = "Erro ao obter pull requests fechados"

        val response = Resource.Error<List<PullRequest>>(errorMessage)

        coEvery { getClosedPullRequestsUseCase(owner, repo) } returns response

        // When
        viewModel.getPullRequestsClosed(owner, repo)

        // Then
        val emittedValues = mutableListOf<Resource<List<PullRequest>>>()
        val observer = Observer<Resource<List<PullRequest>>> { emittedValues.add(it) }
        viewModel.pullRequestsClosed.observeForever(observer)

        advanceUntilIdle()

        assert((emittedValues.last() as Resource.Error).message == errorMessage)

        viewModel.pullRequestsClosed.removeObserver(observer)
    }

}