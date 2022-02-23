package com.dannyjung.githubapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dannyjung.githubapi.data.model.StarCount
import com.dannyjung.githubapi.data.model.StarredRepo

@Dao
interface StarredRepoDao {

    @Query("SELECT * FROM starred_repo LIMIT :limit OFFSET :offset")
    suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepo>

    @Query("SELECT id, stargazers_count FROM starred_repo")
    suspend fun getStarCounts(): List<StarCount>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(starredRepo: StarredRepo)

    @Query("DELETE FROM starred_repo WHERE id = :id")
    suspend fun delete(id: Long)
}
