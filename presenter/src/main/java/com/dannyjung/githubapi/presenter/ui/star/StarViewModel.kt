package com.dannyjung.githubapi.presenter.ui.star

import com.airbnb.mvrx.*
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.dannyjung.githubapi.domain.model.StarredRepoItem
import com.dannyjung.githubapi.domain.usecase.starredrepo.AddStarredRepoUseCase
import com.dannyjung.githubapi.domain.usecase.starredrepo.DeleteStarredRepoUseCase
import com.dannyjung.githubapi.domain.usecase.starredrepo.GetStarCountsUseCase
import com.dannyjung.githubapi.domain.usecase.starredrepo.GetStarredReposUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException

class StarViewModel @AssistedInject constructor(
    @Assisted initialState: StarState,
    private val getStarredReposUseCase: GetStarredReposUseCase,
    private val getStarCountsUseCase: GetStarCountsUseCase,
    private val addStarredRepoUseCase: AddStarredRepoUseCase,
    private val deleteStarredRepoUseCase: DeleteStarredRepoUseCase
) : MavericksViewModel<StarState>(initialState) {

    fun invalidate() = viewModelScope.launch {
        val state = awaitState()

        if (state.getReposAsync is Loading) return@launch

        setState { copy(getReposAsync = Loading()) }

        val reposDeferred = async { getStarredReposUseCase(offset = 0) }
        val repoIdsDeferred = async { getStarCountsUseCase() }

        val reposAsync = reposDeferred.await()
        val repoIdsAsync = repoIdsDeferred.await()

        setState {
            copy(
                getReposAsync = if (reposAsync is Fail || repoIdsAsync is Fail) {
                    Fail(
                        (repoIdsAsync as? Fail)?.error
                            ?: (repoIdsAsync as? Fail)?.error
                            ?: IOException("unknown error")
                    )
                } else {
                    Success(Unit)
                },
                repos = reposAsync(),
                allStarCounts = repoIdsAsync()?.map { it.id to it.stargazersCount }?.toMap()
            )
        }
    }

    fun getStarredRepoNextPage() = viewModelScope.launch {
        val state = awaitState()

        if (state.getReposAsync !is Success ||
            state.repos == null ||
            state.getReposNextPageAsync is Loading
        ) return@launch

        setState { copy(getReposNextPageAsync = Loading()) }

        val async = getStarredReposUseCase(offset = state.repos.size)

        setState {
            copy(
                getReposNextPageAsync = async,
                repos = repos?.plus(async() ?: emptyList())
            )
        }
    }

    fun addRepo(repoItem: StarredRepoItem) = viewModelScope.launch {
        val state = awaitState()

        if (state.addRepoAsync is Loading) return@launch

        setState {
            copy(
                addRepoAsync = Loading(),
                repos = repos?.plus(repoItem),
                allStarCounts = allStarCounts?.plus(repoItem.id to repoItem.stargazersCount + 1)
            )
        }

        val async = addStarredRepoUseCase(repoItem)

        setState {
            copy(
                addRepoAsync = async,
                repos = if (async is Fail) {
                    repos?.minus(repoItem)
                } else {
                    repos
                },
                allStarCounts = if (async is Fail) {
                    allStarCounts?.minus(repoItem.id)
                } else {
                    allStarCounts
                }
            )
        }
    }

    fun deleteRepo(repoItem: StarredRepoItem) = viewModelScope.launch {
        val state = awaitState()

        if (state.deleteRepoAsync is Loading) return@launch

        setState {
            copy(
                deleteRepoAsync = Loading(),
                repos = repos?.minus(repoItem),
                allStarCounts = allStarCounts?.minus(repoItem.id)
            )
        }

        val async = deleteStarredRepoUseCase(repoItem)

        setState {
            copy(
                deleteRepoAsync = async,
                repos = if (async is Fail) {
                    repos?.plus(repoItem)
                } else {
                    repos
                },
                allStarCounts = if (async is Fail) {
                    allStarCounts?.plus(repoItem.id to repoItem.stargazersCount)
                } else {
                    allStarCounts
                }
            )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<StarViewModel, StarState> {
        override fun create(state: StarState): StarViewModel
    }

    companion object :
        MavericksViewModelFactory<StarViewModel, StarState> by hiltMavericksViewModelFactory()
}
