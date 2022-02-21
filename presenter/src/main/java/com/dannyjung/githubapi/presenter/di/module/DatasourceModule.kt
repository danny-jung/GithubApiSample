package com.dannyjung.githubapi.presenter.di.module

import com.dannyjung.githubapi.data.remote.datasource.SearchDataSource
import com.dannyjung.githubapi.data.remote.datasource.SearchDatasourceImpl
import com.dannyjung.githubapi.data.remote.service.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    fun provideSearchDatasource(
        searchService: SearchService
    ): SearchDataSource = SearchDatasourceImpl(searchService)
}
