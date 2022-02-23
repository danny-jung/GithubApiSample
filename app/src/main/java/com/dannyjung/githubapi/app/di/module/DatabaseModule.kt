package com.dannyjung.githubapi.app.di.module

import android.content.Context
import androidx.room.Room
import com.dannyjung.githubapi.data.local.dao.StarredRepoDao
import com.dannyjung.githubapi.data.local.database.StarredRepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideStarredRepoDatabase(
        @ApplicationContext applicationContext: Context
    ): StarredRepoDatabase =
        Room
            .databaseBuilder(
                applicationContext,
                StarredRepoDatabase::class.java,
                "starred_repo.db"
            )
            .build()

    @Singleton
    @Provides
    fun provideStarredRepoDao(starredRepoDatabase: StarredRepoDatabase): StarredRepoDao =
        starredRepoDatabase.starredRepoDao()

}
