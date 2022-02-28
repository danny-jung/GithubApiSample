package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.RepoItemResponse
import com.dannyjung.githubapi.data.model.UserResponse
import com.dannyjung.githubapi.data.remote.service.UserService

class UserRemoteDatasourceImpl(
    private val userService: UserService
) : UserRemoteDataSource {

    override suspend fun getUser(): UserResponse =
        userService.getUser()

    override suspend fun getUserRepos(
        userName: String,
        page: Int,
        pageSize: Int
    ): List<RepoItemResponse> = userService.getUserRepos(
        userName = userName,
        page = page,
        pageSize = pageSize
    )
}
