package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.AccessTokenResponse
import com.dannyjung.githubapi.data.remote.service.AuthService

class AuthRemoteDatasourceImpl(
    private val authService: AuthService
) : AuthRemoteDataSource {

    override suspend fun requestAccessToken(code: String): AccessTokenResponse =
        authService.accessToken(code = code)
}
