package com.dannyjung.githubapi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dannyjung.githubapi.data.local.dao.StarredRepoDao
import com.dannyjung.githubapi.data.model.StarredRepo

@Database(entities = [StarredRepo::class], version = 1, exportSchema = false)
abstract class StarredRepoDatabase : RoomDatabase() {

    abstract fun starredRepoDao(): StarredRepoDao
}
