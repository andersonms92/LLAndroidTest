package com.llandroidtest.di

import com.llandroidtest.data.remote.GithubApi
import com.llandroidtest.data.repository.GithubRepositoryImpl
import com.llandroidtest.domain.repository.GithubRepository
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