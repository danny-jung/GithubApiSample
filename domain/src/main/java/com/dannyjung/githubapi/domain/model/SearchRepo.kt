package com.dannyjung.githubapi.domain.model

data class SearchRepo(
    val totalCount: Int,
    val items: List<RepoItem>,
)
