package com.dannyjung.githubapi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "starred_repo")
data class StarCount(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "stargazers_count") val stargazersCount: Long,
)
