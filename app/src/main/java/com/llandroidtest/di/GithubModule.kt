package com.llandroidtest.di

import com.llandroidtest.data.remote.GithubApi
import com.llandroidtest.domain.repository.GithubRepository
import com.llandroidtest.domain.repository.GithubRepositoryImpl
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
    fun provideGithubRepository(githubApi: GithubApi): GithubRepository {
        return GithubRepositoryImpl(githubApi)
    }
}