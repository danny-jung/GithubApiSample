package com.dannyjung.githubapi.data.local.datasource

import com.airbnb.mvrx.CoroutinesStateStore
import com.dannyjung.githubapi.data.local.preference.SharedPreferenceManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    private val sharedPreferenceManager: SharedPreferenceManager
) : AuthLocalDataSource {

    private val stateStore = CoroutinesStateStore(
        initialState = AuthLocalState(),
        scope = CoroutineScope(coroutineDispatcher)
    )

    override val stream: Flow<String?> by lazy {
        stateStore.flow
            .onStart {
                stateStore.set { copy(accessToken = getAccessToken()) }
            }
            .map { it.accessToken }
    }

    override fun getAccessToken(): String? =
        sharedPreferenceManager.getAccessToken()

    override fun setAccessToken(accessToken: String) =
        sharedPreferenceManager.setAccessToken(accessToken)
            .also { stateStore.set { copy(accessToken = accessToken) } }

    override fun clear() =
        sharedPreferenceManager.clear()
            .also { stateStore.set { copy(accessToken = null) } }
}
