package com.dannyjung.githubapi.data.mapper

import com.dannyjung.githubapi.data.model.StarredRepo
import com.dannyjung.githubapi.domain.model.StarredRepoItem

object StarredRepoMapper {

    fun mapperToStarredRepoItem(starredRepo: StarredRepo): StarredRepoItem =
        StarredRepoItem(
            id = starredRepo.id,
            name = starredRepo.name,
            description = starredRepo.description,
            owner = StarredRepoItem.Owner(
                id = starredRepo.ownerId,
                name = starredRepo.ownerLogin,
                avatarUrl = starredRepo.ownerAvatarUrl
            ),
            stargazersCount = starredRepo.stargazersCount,
            watchersCount = starredRepo.watchersCount,
            url = starredRepo.htmlUrl
        )

    fun mapperToStarredRepo(starredRepoItem: StarredRepoItem): StarredRepo =
        StarredRepo(
            id = starredRepoItem.id,
            name = starredRepoItem.name,
            description = starredRepoItem.description,
            ownerId = starredRepoItem.owner.id,
            ownerLogin = starredRepoItem.owner.name,
            ownerAvatarUrl = starredRepoItem.owner.avatarUrl,
            stargazersCount = starredRepoItem.stargazersCount,
            watchersCount = starredRepoItem.watchersCount,
            htmlUrl = starredRepoItem.url
        )
}
