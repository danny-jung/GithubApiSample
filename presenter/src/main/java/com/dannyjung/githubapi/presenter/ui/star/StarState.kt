package com.dannyjung.githubapi.presenter.ui.star

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dannyjung.githubapi.domain.model.StarredRepoItem

data class StarState(
    val getReposAsync: Async<Unit> = Uninitialized,
    val addRepoAsync: Async<Unit> = Uninitialized,
    val deleteRepoAsync: Async<Unit> = Uninitialized,
    val repos: List<StarredRepoItem>? = null,
    val allRepoIds: Set<Long>? = null
) : MavericksState
