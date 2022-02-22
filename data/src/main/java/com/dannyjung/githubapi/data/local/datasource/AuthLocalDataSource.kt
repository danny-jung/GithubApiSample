package com.dannyjung.githubapi.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {

    val stream: Flow<String?>

    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String)

    fun clear()
}
