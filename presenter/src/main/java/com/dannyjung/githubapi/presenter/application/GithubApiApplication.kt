package com.dannyjung.githubapi.presenter.application

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.dannyjung.githubapi.presenter.BuildConfig
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GithubApiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
