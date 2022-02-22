package com.dannyjung.githubapi.presenter.ui.mypage

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class MyPageState(
    val initialAsync: Async<Unit> = Uninitialized
) : MavericksState
