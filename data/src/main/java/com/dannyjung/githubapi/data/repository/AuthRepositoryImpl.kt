package com.dannyjung.githubapi.data.repository

import com.dannyjung.githubapi.data.di.qualifiers.IoDispatcher
import com.dannyjung.githubapi.data.local.datasource.AuthLocalDataSource
import com.dannyjung.githubapi.data.mapper.AuthMapper
import com.dannyjung.githubapi.data.remote.datasource.AuthRemoteDataSource
import com.dannyjung.githubapi.domain.model.AccessToken
import com.dannyjung.githubapi.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override val stream: Flow<String?>
        get() = authLocalDataSource.stream

    override val accessToken: String?
        get() = authLocalDataSource.getAccessToken()

    override suspend fun requestAccessToken(code: String): AccessToken =
        withContext(coroutineDispatcher) {
            val accessTokenResponse = authRemoteDataSource.requestAccessToken(code)

            authLocalDataSource.setAccessToken(accessTokenResponse.accessToken)

            AuthMapper.mapperToAccessToken(accessTokenResponse)
        }

    override fun clear() = authLocalDataSource.clear()

}
