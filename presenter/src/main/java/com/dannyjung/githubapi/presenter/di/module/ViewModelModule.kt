package com.dannyjung.githubapi.presenter.di.module

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import com.dannyjung.githubapi.presenter.ui.mypage.MyPageViewModel
import com.dannyjung.githubapi.presenter.ui.search.SearchViewModel
import com.dannyjung.githubapi.presenter.ui.star.StarViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(
        factory: SearchViewModel.Factory
    ): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(StarViewModel::class)
    fun bindStarViewModel(
        factory: StarViewModel.Factory
    ): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(MyPageViewModel::class)
    fun bindMyPageViewModel(
        factory: MyPageViewModel.Factory
    ): AssistedViewModelFactory<*, *>
}
