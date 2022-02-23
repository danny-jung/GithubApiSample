package com.dannyjung.githubapi.app.di.module

import com.dannyjung.githubapi.data.local.dao.StarredRepoDao
import com.dannyjung.githubapi.data.local.datasource.AuthLocalDataSource
import com.dannyjung.githubapi.data.local.datasource.AuthLocalDataSourceImpl
import com.dannyjung.githubapi.data.local.datasource.StarredRepoLocalDataSource
import com.dannyjung.githubapi.data.local.datasource.StarredRepoLocalDataSourceImpl
import com.dannyjung.githubapi.data.local.preference.SharedPreferenceManager
import com.dannyjung.githubapi.data.remote.datasource.*
import com.dannyjung.githubapi.data.remote.service.AuthService
import com.dannyjung.githubapi.data.remote.service.SearchService
import com.dannyjung.githubapi.data.remote.service.UserService
import com.dannyjung.githubapi.domain.di.qualifiers.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Singleton
    @Provides
    fun provideSearchRemoteDataSource(
        searchService: SearchService
    ): SearchRemoteDataSource = SearchRemoteDatasourceImpl(searchService)

    @Singleton
    @Provides
    fun provideAuthLocalDataSource(
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
        sharedPreferenceManager: SharedPreferenceManager
    ): AuthLocalDataSource = AuthLocalDataSourceImpl(coroutineDispatcher, sharedPreferenceManager)

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(
        authService: AuthService
    ): AuthRemoteDataSource = AuthRemoteDatasourceImpl(authService)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(
        userService: UserService
    ): UserRemoteDataSource = UserRemoteDatasourceImpl(userService)

    @Singleton
    @Provides
    fun provideStarredRepoLocalDataSource(
        starredRepoDao: StarredRepoDao
    ): StarredRepoLocalDataSource = StarredRepoLocalDataSourceImpl(starredRepoDao)
}
