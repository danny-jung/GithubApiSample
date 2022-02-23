package com.dannyjung.githubapi.domain.usecase.starredrepo

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.dannyjung.githubapi.domain.model.StarredRepoItem
import com.dannyjung.githubapi.domain.repository.StarredRepoRepository
import javax.inject.Inject

class AddStarredRepoUseCase @Inject constructor(
    private val starredRepoRepository: StarredRepoRepository
) {

    suspend operator fun invoke(starredRepoItem: StarredRepoItem): Async<Unit> =
        try {
            Success(starredRepoRepository.insert(starredRepoItem))
        } catch (e: Exception) {
            e.printStackTrace()
            Fail(e)
        }
}
