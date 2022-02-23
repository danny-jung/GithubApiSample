package com.dannyjung.githubapi.domain.repository

import com.dannyjung.githubapi.domain.model.AccessToken
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val stream: Flow<String?>

    val accessToken: String?

    suspend fun requestAccessToken(code: String): AccessToken

    suspend fun clear()
}
