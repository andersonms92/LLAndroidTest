package com.llandroidtest.utils

import com.llandroidtest.data.model.Repository
import com.llandroidtest.data.model.Owner
import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.data.model.User

object MocksRepository {

    val mockOwner = Owner(
        login = "owner1",
        avatarUrl = "https://avatar.com/owner1"
    )

    val mockRepository1 = Repository(
        id = 1L,
        name = "repo1",
        description = "Repo 1 description",
        owner = mockOwner,
        stargazersCount = 100,
        forksCount = 10
    )

    val mockRepository2 = Repository(
        id = 2L,
        name = "repo2",
        description = "Repo 2 description",
        owner = mockOwner,
        stargazersCount = 200,
        forksCount = 20
    )

    val mockRepositories = listOf(mockRepository1, mockRepository2)


    val mockUser = User(
        login = "user1",
        avatarUrl = "https://avatar.com/user1"
    )
    val mockPullRequest1 = PullRequestResponse(
        title = "pr1",
        body = "Description of PR1",
        htmlUrl = "https://github.com/owner1/repo1/pull/1",
        user = mockUser,
        createdAt = "2024-01-01T00:00:00Z",
        state = "open"
    )

    val mockPullRequest2 = PullRequestResponse(
        title = "pr2",
        body = "Description of PR2",
        htmlUrl = "https://github.com/owner1/repo1/pull/2",
        user = mockUser,
        createdAt = "2024-01-02T00:00:00Z",
        state = "closed"
    )

    val mockPullRequests = listOf(mockPullRequest1, mockPullRequest2)
}
