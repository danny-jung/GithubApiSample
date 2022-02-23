package com.dannyjung.githubapi.data.local.datasource

import com.dannyjung.githubapi.data.model.StarCount
import com.dannyjung.githubapi.data.model.StarredRepo

interface StarredRepoLocalDataSource {

    suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepo>

    suspend fun getStarCounts(): List<StarCount>

    suspend fun insert(starredRepo: StarredRepo)

    suspend fun delete(starredRepo: StarredRepo)
}
