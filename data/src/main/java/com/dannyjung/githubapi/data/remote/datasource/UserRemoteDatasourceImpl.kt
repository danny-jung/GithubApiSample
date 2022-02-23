package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.RepoItemResponse
import com.dannyjung.githubapi.data.model.UserResponse
import com.dannyjung.githubapi.data.remote.service.UserService

class UserRemoteDatasourceImpl(
    private val userService: UserService
) : UserRemoteDataSource {

    override suspend fun getUser(token: String?): UserResponse =
        userService.getUser(token = "token $token")

    override suspend fun getUserRepos(
        token: String?,
        userName: String,
        page: Int,
        pageSize: Int
    ): List<RepoItemResponse> = userService.getUserRepos(
        token = "token $token",
        userName = userName,
        page = page,
        pageSize = pageSize
    )
}
