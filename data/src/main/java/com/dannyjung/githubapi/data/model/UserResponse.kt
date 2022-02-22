package com.dannyjung.githubapi.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
)
