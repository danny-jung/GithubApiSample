package com.dannyjung.githubapi.domain.usecase.auth

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.dannyjung.githubapi.domain.model.AccessToken
import com.dannyjung.githubapi.domain.repository.AuthRepository
import javax.inject.Inject

class RequestAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(code: String): Async<AccessToken> =
        try {
            Success(authRepository.requestAccessToken(code))
        } catch (e: Exception) {
            e.printStackTrace()
            Fail(e)
        }
}
