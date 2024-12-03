package com.llandroidtest.presentation.viewmodel

import com.llandroidtest.data.model.Repository
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
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

    private val savedStateHandle: SavedStateHandle = mockk()

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
}