package com.dannyjung.githubapi.presenter.ui.star

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class StarState(
    val initialAsync: Async<Unit> = Uninitialized
) : MavericksState
