package com.dannyjung.githubapi.domain.mapper

import com.dannyjung.githubapi.domain.model.RepoItem
import com.dannyjung.githubapi.domain.model.StarredRepoItem

object StarredRepoItemMapper {

    fun mapperToStarredRepoItem(repoItem: RepoItem): StarredRepoItem =
        StarredRepoItem(
            id = repoItem.id,
            name = repoItem.name,
            description = repoItem.description,
            owner = StarredRepoItem.Owner(
                id = repoItem.owner.id,
                name = repoItem.owner.name,
                avatarUrl = repoItem.owner.avatarUrl
            ),
            stargazersCount = repoItem.stargazersCount,
            watchersCount = repoItem.watchersCount,
            url = repoItem.url
        )
}
