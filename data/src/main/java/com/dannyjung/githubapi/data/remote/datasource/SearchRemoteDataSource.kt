package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.SearchRepoResponse

interface SearchRemoteDataSource {

    suspend fun searchRepositories(
        token: String?,
        query: String,
        page: Int,
        pageSize: Int
    ): SearchRepoResponse
}
