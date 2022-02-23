package com.dannyjung.githubapi.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("company")
    val company: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("html_url")
    val htmlUrl: String
)
