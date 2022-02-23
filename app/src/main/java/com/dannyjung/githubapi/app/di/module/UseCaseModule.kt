package com.dannyjung.githubapi.app.di.module

import com.dannyjung.githubapi.domain.repository.AuthRepository
import com.dannyjung.githubapi.domain.repository.SearchRepository
import com.dannyjung.githubapi.domain.repository.StarredRepoRepository
import com.dannyjung.githubapi.domain.repository.UserRepository
import com.dannyjung.githubapi.domain.usecase.auth.AccessTokenStreamUseCase
import com.dannyjung.githubapi.domain.usecase.auth.ClearAccessTokenUseCase
import com.dannyjung.githubapi.domain.usecase.auth.RequestAccessTokenUseCase
import com.dannyjung.githubapi.domain.usecase.search.SearchRepositoriesUseCase
import com.dannyjung.githubapi.domain.usecase.starredrepo.AddStarredRepoUseCase
import com.dannyjung.githubapi.domain.usecase.starredrepo.DeleteStarredRepoUseCase
import com.dannyjung.githubapi.domain.usecase.starredrepo.GetStarCountsUseCase
import com.dannyjung.githubapi.domain.usecase.starredrepo.GetStarredReposUseCase
import com.dannyjung.githubapi.domain.usecase.user.GetUserReposUseCase
import com.dannyjung.githubapi.domain.usecase.user.GetUserUseCase
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

    @Provides
    fun provideRequestAccessTokenUseCase(
        authRepository: AuthRepository
    ) = RequestAccessTokenUseCase(authRepository)

    @Provides
    fun provideClearAccessTokenUseCase(
        authRepository: AuthRepository
    ) = ClearAccessTokenUseCase(authRepository)

    @Provides
    fun provideAccessTokenStreamUseCase(
        authRepository: AuthRepository
    ) = AccessTokenStreamUseCase(authRepository)

    @Provides
    fun provideGetUserUseCase(
        userRepository: UserRepository
    ) = GetUserUseCase(userRepository)

    @Provides
    fun provideGetUserReposUseCase(
        userRepository: UserRepository
    ) = GetUserReposUseCase(userRepository)

    @Provides
    fun provideGetStarredReposUseCase(
        starredRepoRepository: StarredRepoRepository
    ) = GetStarredReposUseCase(starredRepoRepository)

    @Provides
    fun provideGetStarCountsUseCase(
        starredRepoRepository: StarredRepoRepository
    ) = GetStarCountsUseCase(starredRepoRepository)

    @Provides
    fun provideAddStarredRepoUseCase(
        starredRepoRepository: StarredRepoRepository
    ) = AddStarredRepoUseCase(starredRepoRepository)

    @Provides
    fun provideDeleteStarredRepoUseCase(
        starredRepoRepository: StarredRepoRepository
    ) = DeleteStarredRepoUseCase(starredRepoRepository)
}
