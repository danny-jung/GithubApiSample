package com.dannyjung.githubapi.data.local.datasource

import com.dannyjung.githubapi.data.model.StarCountResponse
import com.dannyjung.githubapi.data.model.StarredRepoResponse

interface StarredRepoLocalDataSource {

    suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepoResponse>

    suspend fun getStarCounts(): List<StarCountResponse>

    suspend fun insert(starredRepo: StarredRepoResponse)

    suspend fun delete(id: Long)

    suspend fun deleteAll()
}
