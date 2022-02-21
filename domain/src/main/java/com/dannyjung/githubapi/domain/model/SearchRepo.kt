package com.dannyjung.githubapi.domain.model

data class SearchRepo(
    val totalCount: Int,
    val items: List<Item>,
)

data class Item(
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
