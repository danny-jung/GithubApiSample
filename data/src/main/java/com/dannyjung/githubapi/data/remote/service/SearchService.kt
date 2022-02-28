package com.dannyjung.githubapi.data.remote.service

import com.dannyjung.githubapi.data.model.SearchRepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("per_page") pageSize: Int,
        @Query("page") page: Int,
    ): SearchRepoResponse
}
