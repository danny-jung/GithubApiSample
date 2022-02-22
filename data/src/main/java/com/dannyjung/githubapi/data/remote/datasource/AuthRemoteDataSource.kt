package com.dannyjung.githubapi.data.remote.datasource

import com.dannyjung.githubapi.data.model.AccessTokenResponse

interface AuthRemoteDataSource {

    suspend fun requestAccessToken(code: String): AccessTokenResponse
}
