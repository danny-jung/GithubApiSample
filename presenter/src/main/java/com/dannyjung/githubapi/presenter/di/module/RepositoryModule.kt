package com.dannyjung.githubapi.presenter.di.module

import com.dannyjung.githubapi.data.repository.AuthRepositoryImpl
import com.dannyjung.githubapi.data.repository.SearchRepositoryImpl
import com.dannyjung.githubapi.domain.repository.AuthRepository
import com.dannyjung.githubapi.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository

    @Singleton
    @Binds
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
