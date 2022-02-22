package com.dannyjung.githubapi.data.model

import com.google.gson.annotations.SerializedName

data class RepoItem(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("stargazers_count")
    val stargazersCount: Long,
    @SerializedName("watchers_count")
    val watchersCount: Long
)

data class Owner(
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)
