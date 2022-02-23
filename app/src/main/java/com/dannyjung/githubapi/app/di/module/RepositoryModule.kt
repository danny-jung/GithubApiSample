package com.dannyjung.githubapi.app.di.module

import com.dannyjung.githubapi.data.repository.AuthRepositoryImpl
import com.dannyjung.githubapi.data.repository.SearchRepositoryImpl
import com.dannyjung.githubapi.data.repository.UserRepositoryImpl
import com.dannyjung.githubapi.domain.repository.AuthRepository
import com.dannyjung.githubapi.domain.repository.SearchRepository
import com.dannyjung.githubapi.domain.repository.UserRepository
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

    @Singleton
    @Binds
    fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
