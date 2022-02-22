package com.dannyjung.githubapi.data.local.preference

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceManagerImpl @Inject constructor(
    @ApplicationContext applicationContext: Context
) : SharedPreferenceManager {

    private val preferences = applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    override fun getAccessToken(): String? =
        preferences.getString(ACCESS_TOKEN, null)

    @Synchronized
    override fun setAccessToken(token: String) =
        preferences.edit {
            putString(ACCESS_TOKEN, token)
        }

    override fun clear() =
        preferences.edit {
            clear()
            commit()
        }

    companion object {

        private const val NAME = "github api"

        private const val ACCESS_TOKEN = "access_token"
    }

}
