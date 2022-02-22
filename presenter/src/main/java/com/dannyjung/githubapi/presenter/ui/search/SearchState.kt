package com.dannyjung.githubapi.presenter.ui.search

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class SearchState(
    val initialAsync: Async<Unit> = Uninitialized
) : MavericksState
