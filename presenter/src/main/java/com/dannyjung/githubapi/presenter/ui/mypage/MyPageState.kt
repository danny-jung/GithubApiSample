package com.dannyjung.githubapi.presenter.ui.mypage

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dannyjung.githubapi.domain.model.RepoItem
import com.dannyjung.githubapi.domain.model.User

data class MyPageState(
    val initialAsync: Async<Unit> = Uninitialized,
    val myRepoNextPageAsync: Async<List<RepoItem>> = Uninitialized,
    val user: User? = null,
    val userRepos: List<RepoItem>? = null,
    val page: Int = 1
) : MavericksState
