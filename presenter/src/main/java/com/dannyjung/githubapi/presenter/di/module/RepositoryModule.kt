package com.dannyjung.githubapi.presenter.di.module

import com.dannyjung.githubapi.data.repository.SearchRepositoryImpl
import com.dannyjung.githubapi.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}
