package com.dannyjung.githubapi.data.local.datasource

import com.dannyjung.githubapi.data.model.StarredRepo

interface StarredRepoLocalDataSource {

    suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepo>

    suspend fun getAllStarredRepoIds(): List<Long>

    suspend fun insert(starredRepo: StarredRepo)

    suspend fun delete(starredRepo: StarredRepo)
}
