package com.dannyjung.githubapi.domain.repository

import com.dannyjung.githubapi.domain.model.RepoItem
import com.dannyjung.githubapi.domain.model.User

interface UserRepository {

    suspend fun getUser(): User

    suspend fun getUserRepos(userName: String, page: Int, pageSize: Int): List<RepoItem>
}
