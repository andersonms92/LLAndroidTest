package com.llandroidtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubModule {

    @Singleton
    @Provides
    fun provideGithubRepository(githubApi: com.data.remote.GithubApi): com.domain.repository.GithubRepository {
        return com.data.repository.GithubRepositoryImpl(githubApi)
    }
}