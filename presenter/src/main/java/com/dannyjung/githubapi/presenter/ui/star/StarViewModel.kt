package com.dannyjung.githubapi.presenter.ui.star

import com.airbnb.mvrx.*
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.dannyjung.githubapi.domain.model.StarredRepoItem
import com.dannyjung.githubapi.domain.usecase.starredrepo.AddStarredRepoUseCase
import com.dannyjung.githubapi.domain.usecase.starredrepo.DeleteStarredRepoUseCase
import com.dannyjung.githubapi.domain.usecase.starredrepo.GetAllStarredRepoIdsUseCase
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
    private val getAllStarredRepoIdsUseCase: GetAllStarredRepoIdsUseCase,
    private val addStarredRepoUseCase: AddStarredRepoUseCase,
    private val deleteStarredRepoUseCase: DeleteStarredRepoUseCase
) : MavericksViewModel<StarState>(initialState) {

    fun invalidate() = viewModelScope.launch {
        val state = awaitState()

        if (state.getReposAsync is Loading) return@launch

        setState { copy(getReposAsync = Loading()) }

        val reposDeferred = async { getStarredReposUseCase(offset = state.repos?.size ?: 0) }
        val repoIdsDeferred = async { getAllStarredRepoIdsUseCase() }

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
                allRepoIds = repoIdsAsync()?.toSet()
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
                allRepoIds = allRepoIds?.plus(repoItem.id)
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
                allRepoIds = if (async is Fail) {
                    allRepoIds?.minus(repoItem.id)
                } else {
                    allRepoIds
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
                allRepoIds = allRepoIds?.minus(repoItem.id)
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
                allRepoIds = if (async is Fail) {
                    allRepoIds?.plus(repoItem.id)
                } else {
                    allRepoIds
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
