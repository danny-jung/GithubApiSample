package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.SearchRepoResponse
import com.dannyjung.githubapi.data.remote.service.SearchService

class SearchRemoteDatasourceImpl(
    private val searchService: SearchService
) : SearchRemoteDataSource {

    override suspend fun searchRepositories(
        token: String?,
        query: String,
        page: Int,
        pageSize: Int
    ): SearchRepoResponse =
        searchService.searchRepositories(
            token = "token $token",
            query = query,
            page = page,
            pageSize = pageSize
        )
}
