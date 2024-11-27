package com.llandroidtest.di

import com.google.gson.GsonBuilder
import com.llandroidtest.data.remote.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://api.github.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }
//}


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun buildClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(5, TimeUnit.MINUTES)
        builder.readTimeout(5, TimeUnit.MINUTES)
        builder.writeTimeout(5, TimeUnit.MINUTES)
        return builder.build()
    }

    @Provides
    @Singleton
    fun retrofitInstance(): GithubApi =
        Retrofit.Builder()
            .baseUrl("https://survey-api.nimblehq.co/api/v1/")
            .client(buildClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().disableHtmlEscaping().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GithubApi::class.java)
}