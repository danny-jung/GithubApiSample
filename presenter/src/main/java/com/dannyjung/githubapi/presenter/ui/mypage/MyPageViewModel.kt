package com.dannyjung.githubapi.presenter.ui.mypage

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MyPageViewModel @AssistedInject constructor(
    @Assisted initialState: MyPageState
) : MavericksViewModel<MyPageState>(initialState) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<MyPageViewModel, MyPageState> {
        override fun create(state: MyPageState): MyPageViewModel
    }

    companion object :
        MavericksViewModelFactory<MyPageViewModel, MyPageState> by hiltMavericksViewModelFactory()
}
