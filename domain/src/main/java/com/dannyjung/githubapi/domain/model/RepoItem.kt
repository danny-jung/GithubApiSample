package com.dannyjung.githubapi.domain.model

data class RepoItem(
    val id: Long,
    val name: String,
    val description: String?,
    val owner: Owner,
    val stargazersCount: Long,
    val watchersCount: Long
)

data class Owner(
    val id: Long,
    val name: String,
    val avatarUrl: String?
)
