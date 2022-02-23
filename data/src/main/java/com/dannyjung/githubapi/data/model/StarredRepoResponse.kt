package com.dannyjung.githubapi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starred_repo")
data class StarredRepoResponse(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "first_name") val name: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "owner_id") val ownerId: Long,
    @ColumnInfo(name = "owner_login") val ownerLogin: String,
    @ColumnInfo(name = "owner_avatar_url") val ownerAvatarUrl: String?,
    @ColumnInfo(name = "stargazers_count") val stargazersCount: Long,
    @ColumnInfo(name = "watchers_count") val watchersCount: Long,
    @ColumnInfo(name = "html_url") val htmlUrl: String
)
