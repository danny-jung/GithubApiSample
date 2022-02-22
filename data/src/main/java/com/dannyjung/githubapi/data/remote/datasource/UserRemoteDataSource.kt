package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.RepoItem
import com.dannyjung.githubapi.data.model.UserResponse

interface UserRemoteDataSource {

    suspend fun getUser(): UserResponse

    suspend fun getUserRepos(
        userName: String,
        page: Int,
        pageSize: Int,
    ): List<RepoItem>
}
