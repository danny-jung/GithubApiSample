package com.dannyjung.githubapi.presenter.ui.star

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class StarViewModel @AssistedInject constructor(
    @Assisted initialState: StarState
) : MavericksViewModel<StarState>(initialState) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<StarViewModel, StarState> {
        override fun create(state: StarState): StarViewModel
    }

    companion object :
        MavericksViewModelFactory<StarViewModel, StarState> by hiltMavericksViewModelFactory()
}
