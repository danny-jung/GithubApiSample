package com.dannyjung.githubapi.presenter.ui.mypage

import com.airbnb.mvrx.*
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.dannyjung.githubapi.domain.usecase.user.GetUserReposUseCase
import com.dannyjung.githubapi.domain.usecase.user.GetUserUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class MyPageViewModel @AssistedInject constructor(
    @Assisted initialState: MyPageState,
    private val getUserUseCase: GetUserUseCase,
    private val getUserReposUseCase: GetUserReposUseCase
) : MavericksViewModel<MyPageState>(initialState) {

    fun initialize() = viewModelScope.launch {
        val state = awaitState()

        if (state.initialAsync is Loading) return@launch

        setState { copy(initialAsync = Loading()) }

        val user = getUserUseCase()
        val userRepos = user()?.let {
            getUserReposUseCase(
                userName = it.name,
                page = 1
            )
        }

        setState {
            copy(
                initialAsync = Success(Unit),
                user = user(),
                userRepos = userRepos?.invoke(),
                page = 1
            )
        }
    }

    fun clear() =
        setState {
            copy(
                initialAsync = Uninitialized,
                user = null,
                userRepos = null,
                page = 1
            )
        }

    fun getMyRepositoriesNextPage() = viewModelScope.launch {
        val state = awaitState()

        if (state.initialAsync !is Success ||
            state.user == null ||
            state.myRepoNextPageAsync is Loading
        ) return@launch

        setState { copy(myRepoNextPageAsync = Loading()) }

        val async = getUserReposUseCase(state.user.name, state.page + 1)

        setState {
            copy(
                myRepoNextPageAsync = async,
                userRepos = userRepos?.plus((async() ?: emptyList()))
            )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<MyPageViewModel, MyPageState> {
        override fun create(state: MyPageState): MyPageViewModel
    }

    companion object :
        MavericksViewModelFactory<MyPageViewModel, MyPageState> by hiltMavericksViewModelFactory()
}
