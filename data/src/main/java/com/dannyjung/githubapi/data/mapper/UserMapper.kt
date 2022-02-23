package com.dannyjung.githubapi.data.mapper

import com.dannyjung.githubapi.data.model.RepoItem
import com.dannyjung.githubapi.data.model.UserResponse
import com.dannyjung.githubapi.domain.model.Owner
import com.dannyjung.githubapi.domain.model.User

object UserMapper {

    fun mapperToUser(userResponse: UserResponse): User =
        User(
            id = userResponse.id,
            name = userResponse.login,
            avatarUrl = userResponse.avatarUrl,
            company = userResponse.company,
            location = userResponse.location,
            url = userResponse.htmlUrl
        )

    fun mapperToUserRepos(
        userReposResponse: List<RepoItem>
    ): List<com.dannyjung.githubapi.domain.model.RepoItem> =
        userReposResponse.map {
            com.dannyjung.githubapi.domain.model.RepoItem(
                id = it.id,
                name = it.name,
                description = it.description,
                owner = Owner(
                    id = it.owner.id,
                    name = it.owner.login,
                    avatarUrl = it.owner.avatarUrl
                ),
                stargazersCount = it.stargazersCount,
                watchersCount = it.watchersCount,
                url = it.htmlUrl
            )
        }
}
