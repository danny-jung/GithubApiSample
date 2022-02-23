package com.dannyjung.githubapi.data.repository

import com.dannyjung.githubapi.data.local.datasource.StarredRepoLocalDataSource
import com.dannyjung.githubapi.data.mapper.StarCountMapper
import com.dannyjung.githubapi.data.mapper.StarredRepoMapper
import com.dannyjung.githubapi.domain.di.qualifiers.IoDispatcher
import com.dannyjung.githubapi.domain.model.StarCount
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

    override suspend fun getStarCounts(): List<StarCount> =
        withContext(coroutineDispatcher) {
            val starCounts = starredRepoLocalDataSource.getStarCounts()

            StarCountMapper.mapperToStarCounts(starCounts)
        }

    override suspend fun insert(starredRepoItem: StarredRepoItem) =
        withContext(coroutineDispatcher) {
            starredRepoLocalDataSource.insert(
                StarredRepoMapper.mapperToStarredRepo(starredRepoItem)
            )
        }

    override suspend fun delete(id: Long) =
        withContext(coroutineDispatcher) {
            starredRepoLocalDataSource.delete(id)
        }
}
