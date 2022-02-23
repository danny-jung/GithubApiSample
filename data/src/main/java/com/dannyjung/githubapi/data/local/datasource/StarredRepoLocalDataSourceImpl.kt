package com.dannyjung.githubapi.data.local.datasource

import com.dannyjung.githubapi.data.local.dao.StarredRepoDao
import com.dannyjung.githubapi.data.model.StarredRepo
import javax.inject.Inject

class StarredRepoLocalDataSourceImpl @Inject constructor(
    private val starredRepoDao: StarredRepoDao
) : StarredRepoLocalDataSource {

    override suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepo> =
        starredRepoDao.getStarredRepos(limit, offset)

    override suspend fun getAllStarredRepoIds(): List<Long> =
        starredRepoDao.getAllStarredRepoIds()

    override suspend fun insert(starredRepo: StarredRepo) =
        starredRepoDao.insert(starredRepo)

    override suspend fun delete(starredRepo: StarredRepo) =
        starredRepoDao.delete(starredRepo)
}
