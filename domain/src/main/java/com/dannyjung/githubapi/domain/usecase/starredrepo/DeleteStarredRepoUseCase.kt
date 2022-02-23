package com.dannyjung.githubapi.domain.usecase.starredrepo

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.dannyjung.githubapi.domain.repository.StarredRepoRepository
import javax.inject.Inject

class DeleteStarredRepoUseCase @Inject constructor(
    private val starredRepoRepository: StarredRepoRepository
) {

    suspend operator fun invoke(id: Long): Async<Unit> =
        try {
            Success(starredRepoRepository.delete(id))
        } catch (e: Exception) {
            e.printStackTrace()
            Fail(e)
        }
}
