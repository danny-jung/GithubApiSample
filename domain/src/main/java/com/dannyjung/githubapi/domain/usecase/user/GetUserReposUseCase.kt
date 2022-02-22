package com.dannyjung.githubapi.domain.usecase.user

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.dannyjung.githubapi.domain.model.RepoItem
import com.dannyjung.githubapi.domain.repository.UserRepository
import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        userName: String,
        page: Int,
        pageSize: Int = 20
    ): Async<List<RepoItem>> =
        try {
            Success(userRepository.getUserRepos(userName, page, pageSize))
        } catch (e: Exception) {
            e.printStackTrace()
            Fail(e)
        }
}
