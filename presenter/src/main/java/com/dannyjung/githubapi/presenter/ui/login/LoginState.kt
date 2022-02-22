package com.dannyjung.githubapi.presenter.ui.login

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dannyjung.githubapi.domain.model.AccessToken

data class LoginState(
    val accessToken: String? = null,
    val requestAccessTokenAsync: Async<AccessToken> = Uninitialized,
    val clearAccessTokenAsync: Async<Unit> = Uninitialized
) : MavericksState
