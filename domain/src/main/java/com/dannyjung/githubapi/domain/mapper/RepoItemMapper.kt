package com.dannyjung.githubapi.domain.mapper

import com.dannyjung.githubapi.domain.model.RepoItem
import com.dannyjung.githubapi.domain.model.StarredRepoItem

object RepoItemMapper {

    fun mapperToRepoItem(starredRepoItem: StarredRepoItem): RepoItem =
        RepoItem(
            id = starredRepoItem.id,
            name = starredRepoItem.name,
            description = starredRepoItem.description,
            owner = RepoItem.Owner(
                id = starredRepoItem.owner.id,
                name = starredRepoItem.owner.name,
                avatarUrl = starredRepoItem.owner.avatarUrl
            ),
            stargazersCount = starredRepoItem.stargazersCount,
            watchersCount = starredRepoItem.watchersCount,
            url = starredRepoItem.url
        )
}
