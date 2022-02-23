package com.dannyjung.githubapi.data.mapper

import com.dannyjung.githubapi.data.model.StarredRepoResponse
import com.dannyjung.githubapi.domain.model.StarredRepoItem

object StarredRepoMapper {

    fun mapperToStarredRepoItem(starredRepo: StarredRepoResponse): StarredRepoItem =
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

    fun mapperToStarredRepoResponse(starredRepoItem: StarredRepoItem): StarredRepoResponse =
        StarredRepoResponse(
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
