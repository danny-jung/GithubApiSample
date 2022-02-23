package com.dannyjung.githubapi.data.mapper

import com.dannyjung.githubapi.data.model.SearchRepoResponse
import com.dannyjung.githubapi.domain.model.RepoItem
import com.dannyjung.githubapi.domain.model.Owner
import com.dannyjung.githubapi.domain.model.SearchRepo

object SearchMapper {

    fun mapperToSearchRepo(searchRepoResponse: SearchRepoResponse): SearchRepo =
        SearchRepo(
            totalCount = searchRepoResponse.totalCount,
            items = searchRepoResponse.items.map { item ->
                RepoItem(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    owner = Owner(
                        id = item.owner.id,
                        name = item.owner.login,
                        avatarUrl = item.owner.avatarUrl
                    ),
                    stargazersCount = item.stargazersCount,
                    watchersCount = item.watchersCount,
                    url = item.htmlUrl
                )
            }
        )
}
