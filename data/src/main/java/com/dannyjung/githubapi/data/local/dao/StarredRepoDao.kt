package com.dannyjung.githubapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dannyjung.githubapi.data.model.StarCountResponse
import com.dannyjung.githubapi.data.model.StarredRepoResponse

@Dao
interface StarredRepoDao {

    @Query("SELECT * FROM starred_repo LIMIT :limit OFFSET :offset")
    suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepoResponse>

    @Query("SELECT id, stargazers_count FROM starred_repo")
    suspend fun getStarCounts(): List<StarCountResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(starredRepo: StarredRepoResponse)

    @Query("DELETE FROM starred_repo WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM starred_repo")
    suspend fun deleteAll()
}
