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

        val updatedRepoItem = repoItem.copy(stargazersCount = repoItem.stargazersCount + 1)

        setState {
            copy(
                addRepoAsync = Loading(),
                repos = repos?.plus(updatedRepoItem),
                allStarCounts = allStarCounts
                    ?.plus(updatedRepoItem.id to updatedRepoItem.stargazersCount)
            )
        }

        val async = addStarredRepoUseCase(updatedRepoItem)

        setState {
            copy(
                addRepoAsync = async,
                repos = if (async is Fail) {
                    repos?.minus(updatedRepoItem)
                } else {
                    repos
                },
                allStarCounts = if (async is Fail) {
                    allStarCounts?.minus(updatedRepoItem.id)
                } else {
                    allStarCounts
                }
            )
        }
    }

    fun deleteRepo(id: Long) = viewModelScope.launch {
        val state = awaitState()

        if (state.deleteRepoAsync is Loading) return@launch

        val tempRepoItem = state.repos?.find { it.id == id }

        setState {
            copy(
                deleteRepoAsync = Loading(),
                repos = repos?.filterNot { it.id == id },
                allStarCounts = allStarCounts?.filterNot { it.key == id }
            )
        }

        val async = deleteStarredRepoUseCase(id)

        setState {
            copy(
                deleteRepoAsync = async,
                repos = if (async is Fail && tempRepoItem != null) {
                    repos?.plus(tempRepoItem)
                } else {
                    repos
                },
                allStarCounts = if (async is Fail && tempRepoItem != null) {
                    allStarCounts?.plus(tempRepoItem.id to tempRepoItem.stargazersCount)
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
