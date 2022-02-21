package com.dannyjung.githubapi.domain.repository

import com.dannyjung.githubapi.domain.model.SearchRepo

interface SearchRepository {

    suspend fun searchRepositories(
        query: String,
        page: Int,
        pageSize: Int
    ): SearchRepo
}
