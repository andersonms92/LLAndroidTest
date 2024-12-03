package com.llandroidtest.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\'\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ!\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lcom/llandroidtest/data/repository/GithubRepositoryImpl;", "Lcom/llandroidtest/data/repository/GithubRepository;", "githubApi", "Lcom/llandroidtest/data/remote/GithubApi;", "(Lcom/llandroidtest/data/remote/GithubApi;)V", "getPullRequests", "", "Lcom/llandroidtest/data/model/PullRequestResponse;", "owner", "", "repo", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPullRequestsClosed", "getRepositories", "Lcom/llandroidtest/data/model/RepositoryResponse;", "query", "page", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class GithubRepositoryImpl implements com.llandroidtest.data.repository.GithubRepository {
    @org.jetbrains.annotations.NotNull
    private final com.llandroidtest.data.remote.GithubApi githubApi = null;
    
    @javax.inject.Inject
    public GithubRepositoryImpl(@org.jetbrains.annotations.NotNull
    com.llandroidtest.data.remote.GithubApi githubApi) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getRepositories(@org.jetbrains.annotations.NotNull
    java.lang.String query, int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.llandroidtest.data.model.RepositoryResponse> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getPullRequests(@org.jetbrains.annotations.NotNull
    java.lang.String owner, @org.jetbrains.annotations.NotNull
    java.lang.String repo, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.llandroidtest.data.model.PullRequestResponse>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getPullRequestsClosed(@org.jetbrains.annotations.NotNull
    java.lang.String owner, @org.jetbrains.annotations.NotNull
    java.lang.String repo, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.llandroidtest.data.model.PullRequestResponse>> $completion) {
        return null;
    }
}