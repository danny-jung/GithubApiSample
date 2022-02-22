package com.dannyjung.githubapi.presenter.ui.search

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dannyjung.githubapi.domain.model.SearchRepo

data class SearchState(
    val searchRepoAsync: Async<SearchRepo> = Uninitialized,
    val searchRepoNextPageAsync: Async<SearchRepo> = Uninitialized,
    val keyword: String? = null,
    val searchRepo: SearchRepo? = null,
    val page: Int = 1
) : MavericksState
