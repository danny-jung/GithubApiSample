package com.dannyjung.githubapi.presenter.di.module

import com.dannyjung.githubapi.data.remote.service.AuthService
import com.dannyjung.githubapi.data.remote.service.SearchService
import com.dannyjung.githubapi.presenter.BuildConfig
import com.dannyjung.githubapi.presenter.di.qualifiers.AuthRetrofit
import com.dannyjung.githubapi.presenter.di.qualifiers.GitHubRetrofit
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(
                        if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    )
                }
            )
            .apply {
                if (BuildConfig.DEBUG) {
                    addNetworkInterceptor(StethoInterceptor())
                }
            }
            .build()

    @AuthRetrofit
    @Provides
    fun provideAuthRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @GitHubRetrofit
    @Provides
    fun provideGithubRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().setLenient().create()

    @Provides
    fun provideAuthService(
        @AuthRetrofit retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    fun provideSearchService(
        @GitHubRetrofit retrofit: Retrofit
    ): SearchService = retrofit.create(SearchService::class.java)
}
