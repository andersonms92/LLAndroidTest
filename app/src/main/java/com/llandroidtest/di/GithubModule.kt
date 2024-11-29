package com.llandroidtest.di

import com.llandroidtest.domain.repository.GithubRepository
import com.llandroidtest.domain.repository.GithubRepositoryImpl
import com.llandroidtest.domain.service.GithubService
import com.llandroidtest.domain.usecase.GithubUseCase
import com.llandroidtest.domain.usecase.GithubUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubModule {

    @Singleton
    @Provides
    fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    @Singleton
    @Provides
    fun provideGithubRepository(githubService: GithubService): GithubRepository {
        return GithubRepositoryImpl(githubService)
    }

    @Singleton
    @Provides
    fun provideGithubUseCase(githubRepository: GithubRepository): GithubUseCase {
        return GithubUseCaseImpl(githubRepository)
    }
}