package com.dannyjung.githubapi.presenter.ui.login

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.dannyjung.githubapi.domain.usecase.auth.AccessTokenStreamUseCase
import com.dannyjung.githubapi.domain.usecase.auth.ClearAccessTokenUseCase
import com.dannyjung.githubapi.domain.usecase.auth.RequestAccessTokenUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel @AssistedInject constructor(
    @Assisted initialState: LoginState,
    accessTokenStreamUseCase: AccessTokenStreamUseCase,
    private val requestAccessTokenUseCase: RequestAccessTokenUseCase,
    private val clearAccessTokenUseCase: ClearAccessTokenUseCase
) : MavericksViewModel<LoginState>(initialState) {

    init {
        accessTokenStreamUseCase()
            .onEach { accessToken ->
                setState { copy(accessToken = accessToken) }
            }
            .launchIn(viewModelScope)
    }

    fun getAuthorizeUrl(): String = AUTHORIZE_URL

    fun requestAccessToken(code: String) = viewModelScope.launch {
        val state = awaitState()

        if (state.requestAccessTokenAsync is Loading) return@launch

        setState { copy(requestAccessTokenAsync = Loading()) }

        val async = requestAccessTokenUseCase(code)

        setState { copy(requestAccessTokenAsync = async) }
    }

    fun clearAccessToken() = viewModelScope.launch {
        val state = awaitState()

        if (state.clearAccessTokenAsync is Loading) return@launch

        setState { copy(clearAccessTokenAsync = Loading()) }

        val async = clearAccessTokenUseCase()

        setState { copy(clearAccessTokenAsync = async) }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<LoginViewModel, LoginState> {
        override fun create(state: LoginState): LoginViewModel
    }

    companion object :
        MavericksViewModelFactory<LoginViewModel, LoginState> by hiltMavericksViewModelFactory() {

        private const val AUTHORIZE_URL =
            "https://github.com/login/oauth/authorize?client_id=d071c8b5e7b25cbf5f7d&scope=repo,user"
    }
}
