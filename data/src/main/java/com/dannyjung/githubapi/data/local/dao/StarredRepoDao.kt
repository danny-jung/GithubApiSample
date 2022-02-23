package com.dannyjung.githubapi.data.local.dao

import androidx.room.*
import com.dannyjung.githubapi.data.model.StarredRepo

@Dao
interface StarredRepoDao {

    @Query("SELECT * FROM starred_repo LIMIT :limit OFFSET :offset")
    suspend fun getStarredRepos(limit: Int, offset: Int): List<StarredRepo>

    @Query("SELECT id FROM starred_repo")
    suspend fun getAllStarredRepoIds(): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(starredRepo: StarredRepo)

    @Delete
    suspend fun delete(starredRepo: StarredRepo)
}
