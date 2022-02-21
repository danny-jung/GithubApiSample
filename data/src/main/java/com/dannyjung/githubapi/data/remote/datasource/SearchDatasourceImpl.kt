package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.SearchRepoResponse
import com.dannyjung.githubapi.data.remote.service.SearchService

class SearchDatasourceImpl(
    private val searchService: SearchService
) : SearchDataSource {

    override suspend fun searchRepositories(
        token: String?,
        query: String,
        page: Int,
        pageSize: Int
    ): SearchRepoResponse =
        searchService.searchRepositories(
            token = token,
            query = query,
            page = page,
            pageSize = pageSize
        )
}
