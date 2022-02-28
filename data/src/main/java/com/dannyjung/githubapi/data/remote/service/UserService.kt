package com.dannyjung.githubapi.data.remote.service

import com.dannyjung.githubapi.data.model.RepoItemResponse
import com.dannyjung.githubapi.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("user")
    suspend fun getUser(): UserResponse

    @GET("users/{user_name}/repos")
    suspend fun getUserRepos(
        @Path("user_name") userName: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
    ): List<RepoItemResponse>
}
