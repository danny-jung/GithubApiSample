package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.RepoItem
import com.dannyjung.githubapi.data.model.UserResponse
import com.dannyjung.githubapi.data.remote.service.UserService

class UserRemoteDatasourceImpl(
    private val userService: UserService
) : UserRemoteDataSource {

    override suspend fun getUser(token: String?): UserResponse =
        userService.getUser(token)

    override suspend fun getUserRepos(
        token: String?,
        userName: String,
        page: Int,
        pageSize: Int
    ): List<RepoItem> = userService.getUserRepos(token, userName, page, pageSize)
}
