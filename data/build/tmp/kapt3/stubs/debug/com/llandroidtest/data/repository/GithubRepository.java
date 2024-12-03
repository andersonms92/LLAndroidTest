package com.llandroidtest.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\'\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\'\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ!\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/llandroidtest/data/repository/GithubRepository;", "", "getPullRequests", "", "Lcom/llandroidtest/data/model/PullRequestResponse;", "owner", "", "repo", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPullRequestsClosed", "getRepositories", "Lcom/llandroidtest/data/model/RepositoryResponse;", "query", "page", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface GithubRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRepositories(@org.jetbrains.annotations.NotNull
    java.lang.String query, int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.llandroidtest.data.model.RepositoryResponse> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPullRequests(@org.jetbrains.annotations.NotNull
    java.lang.String owner, @org.jetbrains.annotations.NotNull
    java.lang.String repo, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.llandroidtest.data.model.PullRequestResponse>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPullRequestsClosed(@org.jetbrains.annotations.NotNull
    java.lang.String owner, @org.jetbrains.annotations.NotNull
    java.lang.String repo, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.llandroidtest.data.model.PullRequestResponse>> $completion);
}