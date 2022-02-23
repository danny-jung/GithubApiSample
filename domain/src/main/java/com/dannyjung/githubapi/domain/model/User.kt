package com.dannyjung.githubapi.domain.model

data class User(
    val id: Long,
    val name: String,
    val avatarUrl: String?,
    val company: String,
    val location: String,
    val url: String,
)
