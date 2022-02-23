package com.dannyjung.githubapi.domain.repository

import com.dannyjung.githubapi.domain.model.StarCount
import com.dannyjung.githubapi.domain.model.StarredRepoItem

interface StarredRepoRepository {

    suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepoItem>

    suspend fun getStarCounts(): List<StarCount>

    suspend fun insert(starredRepoItem: StarredRepoItem)

    suspend fun delete(id: Long)
}
