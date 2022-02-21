package com.dannyjung.githubapi.data.repository

import com.dannyjung.githubapi.data.di.qualifiers.IoDispatcher
import com.dannyjung.githubapi.data.mapper.SearchMapper
import com.dannyjung.githubapi.data.remote.datasource.SearchDataSource
import com.dannyjung.githubapi.domain.model.SearchRepo
import com.dannyjung.githubapi.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val searchDatasource: SearchDataSource,
) : SearchRepository {

    override suspend fun searchRepositories(
        query: String,
        page: Int,
        pageSize: Int
    ): SearchRepo =
        withContext(coroutineDispatcher) {
            val searchRepoResponse = searchDatasource.searchRepositories(
                token = null, // TODO
                query = query,
                page = page,
                pageSize = pageSize
            )

            SearchMapper.mapperToSearchRepo(searchRepoResponse)
        }
}
