package com.llandroidtest.di

import com.llandroidtest.data.remote.GithubApi
import com.llandroidtest.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) : GithubApi {
        return retrofit.create(GithubApi::class.java)
    }
}

//@Provides
//@Singleton
//fun provideGithubApi(client: OkHttpClient): GithubApi =
//    Retrofit.Builder()
//        .baseUrl("https://api.github.com/")
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().disableHtmlEscaping().create()))
//        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//        .build()
//        .create(GithubApi::class.java)
//
//@Provides
//@Singleton
//fun provideOkHttpClient(): OkHttpClient =
//    OkHttpClient.Builder()
//        .connectTimeout(5, TimeUnit.MINUTES)
//        .readTimeout(5, TimeUnit.MINUTES)
//        .writeTimeout(5, TimeUnit.MINUTES)
//        .build()