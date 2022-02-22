package com.dannyjung.githubapi.data.remote.service

import com.dannyjung.githubapi.data.model.RepoItem
import com.dannyjung.githubapi.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("user")
    suspend fun getUser(
        @Header("Authorization") token: String?,
    ): UserResponse

    @GET("users/{user_name}/repos")
    suspend fun getUserRepos(
        @Header("Authorization") token: String?,
        @Path("user_name") userName: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
    ): List<RepoItem>
}
