package com.llandroidtest.data.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ+\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ/\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0003\u0010\r\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lcom/llandroidtest/data/remote/GithubApi;", "", "getPullRequests", "", "Lcom/llandroidtest/data/model/PullRequestResponse;", "owner", "", "repo", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPullRequestsClosed", "getRepositories", "Lcom/llandroidtest/data/model/RepositoryResponse;", "query", "sort", "page", "", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface GithubApi {
    
    @retrofit2.http.GET(value = "search/repositories")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRepositories(@retrofit2.http.Query(value = "q")
    @org.jetbrains.annotations.NotNull
    java.lang.String query, @retrofit2.http.Query(value = "sort")
    @org.jetbrains.annotations.NotNull
    java.lang.String sort, @retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.llandroidtest.data.model.RepositoryResponse> $completion);
    
    @retrofit2.http.GET(value = "repos/{owner}/{repo}/pulls")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPullRequests(@retrofit2.http.Path(value = "owner")
    @org.jetbrains.annotations.NotNull
    java.lang.String owner, @retrofit2.http.Path(value = "repo")
    @org.jetbrains.annotations.NotNull
    java.lang.String repo, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.llandroidtest.data.model.PullRequestResponse>> $completion);
    
    @retrofit2.http.GET(value = "/repos/{owner}/{repo}/pulls?state=closed")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPullRequestsClosed(@retrofit2.http.Path(value = "owner")
    @org.jetbrains.annotations.NotNull
    java.lang.String owner, @retrofit2.http.Path(value = "repo")
    @org.jetbrains.annotations.NotNull
    java.lang.String repo, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.llandroidtest.data.model.PullRequestResponse>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}