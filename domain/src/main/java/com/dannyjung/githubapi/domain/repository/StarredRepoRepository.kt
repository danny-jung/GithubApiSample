package com.dannyjung.githubapi.domain.repository

import com.dannyjung.githubapi.domain.model.StarredRepoItem

interface StarredRepoRepository {

    suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepoItem>

    suspend fun getAllStarredRepoIds(): List<Long>

    suspend fun insert(starredRepoItem: StarredRepoItem)

    suspend fun delete(starredRepoItem: StarredRepoItem)
}
