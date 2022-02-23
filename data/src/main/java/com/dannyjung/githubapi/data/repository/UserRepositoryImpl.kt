package com.dannyjung.githubapi.data.repository

import com.dannyjung.githubapi.data.local.datasource.AuthLocalDataSource
import com.dannyjung.githubapi.data.mapper.UserMapper
import com.dannyjung.githubapi.data.remote.datasource.UserRemoteDataSource
import com.dannyjung.githubapi.domain.di.qualifiers.IoDispatcher
import com.dannyjung.githubapi.domain.model.RepoItem
import com.dannyjung.githubapi.domain.model.User
import com.dannyjung.githubapi.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : UserRepository {

    override suspend fun getUser(): User =
        withContext(coroutineDispatcher) {
            val userResponse = userRemoteDataSource.getUser(authLocalDataSource.getAccessToken())

            UserMapper.mapperToUser(userResponse)
        }

    override suspend fun getUserRepos(userName: String, page: Int, pageSize: Int): List<RepoItem> =
        withContext(coroutineDispatcher) {
            val userReposResponse = userRemoteDataSource.getUserRepos(
                token = authLocalDataSource.getAccessToken(),
                userName = userName,
                page = page,
                pageSize = pageSize
            )

            UserMapper.mapperToUserRepoItems(userReposResponse)
        }
}
