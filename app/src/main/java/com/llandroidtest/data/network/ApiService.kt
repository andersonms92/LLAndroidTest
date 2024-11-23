package com.llandroidtest.data.network

interface ApiService {
    @GET("repositories")
    suspend fun getRepositories(): List<Repository>
