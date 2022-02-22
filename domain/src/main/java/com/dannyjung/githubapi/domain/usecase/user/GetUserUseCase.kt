package com.dannyjung.githubapi.domain.usecase.user

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.dannyjung.githubapi.domain.model.User
import com.dannyjung.githubapi.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Async<User> =
        try {
            Success(userRepository.getUser())
        } catch (e: Exception) {
            e.printStackTrace()
            Fail(e)
        }
}
