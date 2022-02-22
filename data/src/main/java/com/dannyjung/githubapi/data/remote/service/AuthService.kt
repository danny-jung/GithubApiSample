package com.dannyjung.githubapi.data.remote.service

import com.dannyjung.githubapi.data.BuildConfig
import com.dannyjung.githubapi.data.model.AccessTokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("access_token")
    suspend fun accessToken(
        @Field("client_id") id: String = BuildConfig.CLIENT_ID,
        @Field("client_secret") key: String = BuildConfig.CLIENT_SECRET,
        @Field("code") code: String
    ): AccessTokenResponse
}
