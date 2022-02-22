package com.dannyjung.githubapi.domain.model

data class AccessToken(
    val accessToken: String,
    val scope: String,
    val tokenType: String
)
