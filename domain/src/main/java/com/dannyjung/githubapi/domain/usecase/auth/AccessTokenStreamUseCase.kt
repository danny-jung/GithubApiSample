package com.dannyjung.githubapi.domain.usecase.auth

import com.dannyjung.githubapi.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccessTokenStreamUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Flow<String?> =
        authRepository.stream
}
