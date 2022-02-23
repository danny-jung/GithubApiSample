package com.dannyjung.githubapi.domain.usecase.auth

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.dannyjung.githubapi.domain.repository.AuthRepository
import javax.inject.Inject

class ClearAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Async<Unit> =
        try {
            authRepository.clear()
            Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Fail(e)
        }
}
