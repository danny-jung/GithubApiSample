package com.dannyjung.githubapi.domain.usecase.starredrepo

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.dannyjung.githubapi.domain.model.StarredRepoItem
import com.dannyjung.githubapi.domain.repository.StarredRepoRepository
import javax.inject.Inject

class GetStarredReposUseCase @Inject constructor(
    private val starredRepoRepository: StarredRepoRepository
) {

    suspend operator fun invoke(limit: Int = 20, offset: Int): Async<List<StarredRepoItem>> =
        try {
            Success(starredRepoRepository.getStarredRepos(limit, offset))
        } catch (e: Exception) {
            e.printStackTrace()
            Fail(e)
        }
}
