package com.dannyjung.githubapi.domain.usecase.starredrepo

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.dannyjung.githubapi.domain.model.StarCount
import com.dannyjung.githubapi.domain.repository.StarredRepoRepository
import javax.inject.Inject

class GetStarCountsUseCase @Inject constructor(
    private val starredRepoRepository: StarredRepoRepository
) {

    suspend operator fun invoke(): Async<List<StarCount>> =
        try {
            Success(starredRepoRepository.getStarCounts())
        } catch (e: Exception) {
            e.printStackTrace()
            Fail(e)
        }
}
