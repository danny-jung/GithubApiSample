package com.dannyjung.githubapi.data.mapper

import com.dannyjung.githubapi.data.model.AccessTokenResponse
import com.dannyjung.githubapi.domain.model.AccessToken

object AuthMapper {

    fun mapperToAccessToken(accessTokenResponse: AccessTokenResponse): AccessToken =
        AccessToken(
            accessToken = accessTokenResponse.accessToken,
            scope = accessTokenResponse.scope,
            tokenType = accessTokenResponse.tokenType
        )
}
