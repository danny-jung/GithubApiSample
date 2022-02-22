package com.dannyjung.githubapi.presenter.ui.search

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SearchViewModel @AssistedInject constructor(
    @Assisted initialState: SearchState
) : MavericksViewModel<SearchState>(initialState) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<SearchViewModel, SearchState> {
        override fun create(state: SearchState): SearchViewModel
    }

    companion object :
        MavericksViewModelFactory<SearchViewModel, SearchState> by hiltMavericksViewModelFactory()
}
