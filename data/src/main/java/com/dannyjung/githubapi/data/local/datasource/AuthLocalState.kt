package com.dannyjung.githubapi.data.local.datasource

import com.airbnb.mvrx.MavericksState

data class AuthLocalState(
    val accessToken: String? = null
) : MavericksState
