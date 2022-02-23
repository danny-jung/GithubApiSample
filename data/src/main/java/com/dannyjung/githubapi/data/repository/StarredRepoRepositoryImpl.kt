package com.dannyjung.githubapi.data.repository

import com.dannyjung.githubapi.data.local.datasource.StarredRepoLocalDataSource
import com.dannyjung.githubapi.data.mapper.StarredRepoMapper
import com.dannyjung.githubapi.domain.di.qualifiers.IoDispatcher
import com.dannyjung.githubapi.domain.model.StarredRepoItem
import com.dannyjung.githubapi.domain.repository.StarredRepoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StarredRepoRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val starredRepoLocalDataSource: StarredRepoLocalDataSource
) : StarredRepoRepository {

    override suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepoItem> =
        withContext(coroutineDispatcher) {
            val starredRepos = starredRepoLocalDataSource.getStarredRepos(limit, offset)

            starredRepos.map { starredRepo ->
                StarredRepoMapper.mapperToStarredRepoItem(starredRepo)
            }
        }

    override suspend fun getAllStarredRepoIds(): List<Long> =
        withContext(coroutineDispatcher) {
            starredRepoLocalDataSource.getAllStarredRepoIds()
        }

    override suspend fun insert(starredRepoItem: StarredRepoItem) =
        withContext(coroutineDispatcher) {
            starredRepoLocalDataSource.insert(
                StarredRepoMapper.mapperToStarredRepo(starredRepoItem)
            )
        }

    override suspend fun delete(starredRepoItem: StarredRepoItem) =
        withContext(coroutineDispatcher) {
            starredRepoLocalDataSource.delete(
                StarredRepoMapper.mapperToStarredRepo(starredRepoItem)
            )
        }
}
