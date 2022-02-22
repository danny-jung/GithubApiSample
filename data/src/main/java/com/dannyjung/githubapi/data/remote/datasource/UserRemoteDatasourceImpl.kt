package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.RepoItem
import com.dannyjung.githubapi.data.model.UserResponse
import com.dannyjung.githubapi.data.remote.service.UserService

class UserRemoteDatasourceImpl(
    private val userService: UserService
) : UserRemoteDataSource {

    override suspend fun getUser(): UserResponse =
        userService.getUser()

    override suspend fun getUserRepos(userName: String, page: Int, pageSize: Int): List<RepoItem> =
        userService.getUserRepos(userName, page, pageSize)
}
