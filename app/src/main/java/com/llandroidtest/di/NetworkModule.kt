package com.llandroidtest.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.data.utils.Constants.Companion.BASE_URL
import com.google.android.apps.common.testing.accessibility.framework.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CACHE_SIZE = 10 * 1024 * 1024

    @Singleton
    @Provides
    fun provideCache(@ApplicationContext  context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE.toLong())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache, @ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                    .apply {
                        request = if (isNetworkAvailable(context)) {
                            request.newBuilder().header("Cache-Control", "public, max-age=5").build()
                        } else {
                            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=86400").build()
                        }
                    }
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) : com.data.remote.GithubApi {
        return retrofit.create(com.data.remote.GithubApi::class.java)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}