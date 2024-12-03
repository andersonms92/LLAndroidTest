// Generated by Dagger (https://dagger.dev).
package com.llandroidtest.data.repository;

import com.llandroidtest.data.remote.GithubApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class GithubRepositoryImpl_Factory implements Factory<GithubRepositoryImpl> {
  private final Provider<GithubApi> githubApiProvider;

  public GithubRepositoryImpl_Factory(Provider<GithubApi> githubApiProvider) {
    this.githubApiProvider = githubApiProvider;
  }

  @Override
  public GithubRepositoryImpl get() {
    return newInstance(githubApiProvider.get());
  }

  public static GithubRepositoryImpl_Factory create(Provider<GithubApi> githubApiProvider) {
    return new GithubRepositoryImpl_Factory(githubApiProvider);
  }

  public static GithubRepositoryImpl newInstance(GithubApi githubApi) {
    return new GithubRepositoryImpl(githubApi);
  }
}
