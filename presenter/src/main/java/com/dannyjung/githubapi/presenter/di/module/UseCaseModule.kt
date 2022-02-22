package com.dannyjung.githubapi.presenter.di.module

import com.dannyjung.githubapi.domain.repository.SearchRepository
import com.dannyjung.githubapi.domain.usecase.search.SearchRepositoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideSearchRepositoriesUseCase(
        searchRepository: SearchRepository
    ) = SearchRepositoriesUseCase(searchRepository)
}
