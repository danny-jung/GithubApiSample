package com.dannyjung.githubapi.data.remote.interceptor

import com.dannyjung.githubapi.data.local.datasource.AuthLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .apply {
                authLocalDataSource.getAccessToken()?.let { token ->
                    addHeader("Authorization", "token $token")
                }
            }
            .build()

        return chain.proceed(request)
    }
}
