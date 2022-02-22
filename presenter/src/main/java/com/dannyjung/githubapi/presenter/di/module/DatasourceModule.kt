package com.dannyjung.githubapi.presenter.di.module

import com.dannyjung.githubapi.data.di.qualifiers.IoDispatcher
import com.dannyjung.githubapi.data.local.datasource.AuthLocalDataSource
import com.dannyjung.githubapi.data.local.datasource.AuthLocalDataSourceImpl
import com.dannyjung.githubapi.data.local.preference.SharedPreferenceManager
import com.dannyjung.githubapi.data.remote.datasource.AuthRemoteDataSource
import com.dannyjung.githubapi.data.remote.datasource.AuthRemoteDatasourceImpl
import com.dannyjung.githubapi.data.remote.datasource.SearchRemoteDataSource
import com.dannyjung.githubapi.data.remote.datasource.SearchRemoteDatasourceImpl
import com.dannyjung.githubapi.data.remote.service.AuthService
import com.dannyjung.githubapi.data.remote.service.SearchService
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
}
