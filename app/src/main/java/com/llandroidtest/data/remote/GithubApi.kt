package com.llandroidtest.data.remote

import com.llandroidtest.data.model.PullRequestResponse
import com.llandroidtest.data.model.RepositoryResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int
    ): RepositoryResponse

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<PullRequestResponse>

    @GET("/repos/{owner}/{repo}/pulls?state=closed")
    suspend fun getPullRequestsClosed(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<PullRequestResponse>
}