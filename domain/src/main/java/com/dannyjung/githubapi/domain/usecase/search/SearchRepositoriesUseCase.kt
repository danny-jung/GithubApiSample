package com.dannyjung.githubapi.domain.usecase.search

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.dannyjung.githubapi.domain.model.SearchRepo
import com.dannyjung.githubapi.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int,
        pageSize: Int
    ): Async<SearchRepo> =
        try {
            Success(
                searchRepository.searchRepositories(
                    query = query,
                    page = page,
                    pageSize = pageSize
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Fail(e)
        }
}
