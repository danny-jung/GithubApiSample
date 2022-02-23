package com.dannyjung.githubapi.data.repository

import com.dannyjung.githubapi.data.local.datasource.AuthLocalDataSource
import com.dannyjung.githubapi.data.mapper.SearchMapper
import com.dannyjung.githubapi.data.remote.datasource.SearchRemoteDataSource
import com.dannyjung.githubapi.domain.di.qualifiers.IoDispatcher
import com.dannyjung.githubapi.domain.model.SearchRepo
import com.dannyjung.githubapi.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val searchRemoteDatasource: SearchRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : SearchRepository {

    override suspend fun searchRepositories(
        query: String,
        page: Int,
        pageSize: Int
    ): SearchRepo =
        withContext(coroutineDispatcher) {
            val searchRepoResponse = searchRemoteDatasource.searchRepositories(
                token = authLocalDataSource.getAccessToken(),
                query = query,
                page = page,
                pageSize = pageSize
            )

            SearchMapper.mapperToSearchRepo(searchRepoResponse)
        }
}
