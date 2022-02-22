package com.dannyjung.githubapi.data.local.preference

interface SharedPreferenceManager {

    fun getAccessToken(): String?

    fun setAccessToken(token: String)

    fun clear()
}
