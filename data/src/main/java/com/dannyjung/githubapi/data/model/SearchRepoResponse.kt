package com.dannyjung.githubapi.data.model

import com.google.gson.annotations.SerializedName

data class SearchRepoResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val items: List<RepoItem>,
)
