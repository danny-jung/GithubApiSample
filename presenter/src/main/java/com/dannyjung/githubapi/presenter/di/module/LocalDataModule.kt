package com.dannyjung.githubapi.presenter.di.module

import com.dannyjung.githubapi.data.local.preference.SharedPreferenceManager
import com.dannyjung.githubapi.data.local.preference.SharedPreferenceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataModule {

    @Singleton
    @Binds
    fun bindSharedPreferenceManager(
        sharedPreferenceManagerImpl: SharedPreferenceManagerImpl
    ): SharedPreferenceManager
}
