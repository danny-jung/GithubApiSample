package com.dannyjung.githubapi.data.local.datasource

import com.dannyjung.githubapi.data.local.dao.StarredRepoDao
import com.dannyjung.githubapi.data.model.StarCountResponse
import com.dannyjung.githubapi.data.model.StarredRepoResponse
import javax.inject.Inject

class StarredRepoLocalDataSourceImpl @Inject constructor(
    private val starredRepoDao: StarredRepoDao
) : StarredRepoLocalDataSource {

    override suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepoResponse> =
        starredRepoDao.getStarredRepos(limit, offset)

    override suspend fun getStarCounts(): List<StarCountResponse> =
        starredRepoDao.getStarCounts()

    override suspend fun insert(starredRepo: StarredRepoResponse) =
        starredRepoDao.insert(starredRepo)

    override suspend fun delete(id: Long) =
        starredRepoDao.delete(id)

    override suspend fun deleteAll() =
        starredRepoDao.deleteAll()
}
